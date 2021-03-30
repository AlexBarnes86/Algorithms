#!/usr/bin/env ruby 

require 'optparse'
require 'csv'

class Item
	attr_accessor :name, :weight, :value

  def initialize(name, weight, value)
    @name = name
    @weight = weight
    @value = value
  end

end

class Chromosome
  include Comparable
  attr_accessor :bits, :fitness

  def initialize(bits, fitness=0)
    @bits = bits
    @fitness = fitness
  end

  def <=> compare
    fitness <=> compare.fitness
  end

  def to_s
    puts fitness
    puts bits
  end
end

class GeneticAlgorithm
  attr_reader :population_size, :max_generations, :crossover_rate, :mutation_rate, :population, :total_fitness, :distribution
  attr_reader :input_file
  attr_reader :items, :weight, :max_value
  attr_reader :best_so_far


  def initialize(pop_size, generations, crossover, mutation, distribution)
    @population_size = pop_size 
    @max_generations = generations
    @crossover_rate = crossover
    @mutation_rate = mutation
    @distribution = distribution
    @total_fitness = 0
    @population = []
  end

	def crossover(str1, str2)
    return if rand() > @crossover_rate

    rand(str1.size-1).upto(str1.size-1) do |i|
      temp = str1[i]
      str1[i] = str2[i]
      str2[i] = temp
    end
  end

	def mutate bits
    0.upto(bits.size-1) do |i|
      if rand() < @mutation_rate
        bits[i] = (bits[i] == "0" ? "1" : "0")
      end
    end
  end

	def roulette
    selection = rand() * @total_fitness
    sum = 0
    @population.each do |c|
      sum += c.fitness
      return c if sum >= selection
    end

    puts "Roulette error!"
    return @population[rand(@population.size)] #if something goes horribly wrong?
  end

  def random_bits size
    str = ""
    size.times do
      if rand() < @distribution
        str << "1"
      else
        str << "0"
      end
    end
    return str
  end

  def print_chromosome c
    total_weight = 0
    total_value = 0
    puts c.bits
    items = []
    0.upto(c.bits.size) do |i|
      if c.bits[i..i] == '1'
        total_weight += @items[i].weight
        total_value += @items[i].value
        items << @items[i].name
      end
    end
    puts items.join "; "
    puts "Fitness: #{c.fitness}"
    puts "Total weight #{total_weight}"
    puts "Total value: #{total_value}"
  end

  def assign_fitness bits
    total_weight = 0
    total_value = 0
    0.upto(bits.size-1) do |i|
      total_weight += @items[i].weight if bits[i..i] == "1"
      total_value += @items[i].value if bits[i..i] == "1"
    end

    return 0 if total_weight > @weight
    return -1 if @max_value == total_value
    return total_value
  end

  def print_stats
    total_ones = 0
    total_zeros = 0
    ratios = []
    0.upto(@population.size-1) do |i|
      chromo = @population[i]
      chromo_ones = 0
      chromo_zeros = 0
      0.upto(chromo.bits.size-1) do |i|
        chromo_ones += 1 if chromo.bits[i..i] == "1"
        chromo_zeros += 1 if chromo.bits[i..i] == "0"
      end
      ratios << chromo_ones / (1.0*chromo_ones+chromo_zeros)
      total_ones += chromo_ones
      total_zeros += chromo_zeros
    end
    puts "Population stats (1 to 0 ratio):"
    puts "Ratios: #{ratios.join (", ")}"
    puts "Total ratio: #{total_ones / (1.0*total_ones+total_zeros)}"
  end

	def run(items, weight)
    @items = items
    @weight = weight
    @max_value = 0

    @items.each {|i| @max_value += i.value}

    #initial random population
    @best_so_far = Chromosome.new("0"*@items.size, 0)

    @population_size.times do
      bits = random_bits(@items.size)
      fitness = assign_fitness(bits)
      chromo = Chromosome.new(bits, fitness)
      if(chromo > @best_so_far)
        @best_so_far.fitness = chromo.fitness
        @best_so_far.bits = chromo.bits.clone
        print_chromosome @best_so_far
      end
      @population << chromo
    end

    @max_generations.times do
      print '.'
      STDOUT.flush
      @total_fitness = 0

      @population.each do |c|
        c.fitness = assign_fitness(c.bits)
        if(c > @best_so_far)
          #omg, wtf was this cloning bullshit
          # do not use clone directly on Chromosomes due to float's not working?
          @best_so_far.fitness = c.fitness
          @best_so_far.bits = c.bits.clone
          puts ""
          print_stats
          print_chromosome @best_so_far
        end

        @total_fitness += c.fitness
        break if c.fitness == -1
      end

      next_gen = []
      while next_gen.size < @population_size 
        c1 = roulette
        c2 = roulette
        crossover(c1.bits, c2.bits)
        mutate c1.bits
        mutate c2.bits
        next_gen << c1
        next_gen << c2
      end

      @population = next_gen
    end

    puts "All items fit in the bag!" if @best_so_far.fitness == -1
    puts "Done searching, best solution found:"
    print_chromosome @best_so_far

    #@items.each do |i|
    #  puts i.inspect
    #end
	end

  def brute_force items, weight
    @items = items
    @weight = weight

    bits = random_bits(@items.size)
    fitness = assign_fitness(bits)
    @best_so_far = Chromosome.new(bits, fitness)
    print_chromosome @best_so_far

    0.upto(@max_generations) do 
      @distribution = rand() * 0.2
      bits = random_bits(@items.size)
      fitness = assign_fitness(bits) 
      chromosome = Chromosome.new(bits, fitness)
      if(chromosome > @best_so_far)
        @best_so_far.fitness = chromosome.fitness
        @best_so_far.bits = chromosome.bits.clone
        print_chromosome(@best_so_far)
      end
    end
    
  end
end

weight = 100
mutation = 0.001
crossover = 0.7
pop_size = 1000
generations = 1000
distribution = 0.05
input_file = nil
items = []

options = {}
optparse = OptionParser.new do |opts|
  opts.banner = "knapsack [options]"

  opts.on('-w', '--weight WEIGHT', "Set the bag's weight capacity to WEIGHT, default is #{weight}") do |w|
    weight = w.to_f
  end

  opts.on('-m', '--mutation RATE', "Set the mutation RATE, default is #{mutation}") do |m|
    mutation = m.to_f
  end

  opts.on('-c', '--crossover RATE', "Set the crossover RATE, default is #{crossover}") do |c|
    crossover = c.to_f
  end

  opts.on('-g', '--generations SIZE', "Set the maximum number of generations to SIZE, default is #{generations}") do |s|
    generations = s.to_i
  end

  opts.on('-p', '--population SIZE', "Set the population SIZE, default is #{pop_size}") do |s|
    pop_size = s.to_i
  end

  opts.on('-i', '--input FILE', 'Read items from csv FILE, default reads from STDIN') do |f|
    input_file = f
  end

  opts.on('-d', '--distribution PERCENT', "PERCENT of initial population bits are 1's, default is #{distribution}") do |d|
    distribution = d.to_f
  end

  opts.on('-h', '--help', 'Display this screen') do
    puts opts
    exit
  end
end

optparse.parse!

begin
  unless input_file.nil?
    CSV::Reader.parse(File.open(input_file, 'r')) do |row|
      items << Item.new(row[0], row[1].to_f, row[2].to_f)
    end
  else
    #while(input = gets.chomp)
    #  break if(input.nil? or input.empty?)
    #  tokens = input.split(",")
    #  items << Item.new(tokens[0], tokens[1].to_f, tokens[2].to_f)
    #end
    puts "STDIN not currently supported"
  end
rescue
  puts "There was an error reading #{input_file}!"
  puts "Please make sure your input is csv formatted as such:"
  puts "Name, weight, value"
  exit
end

genetic = GeneticAlgorithm.new(pop_size, generations, crossover, mutation, distribution)
total_weight = (items.collect{|i| i.weight}).inject{|sum, x| sum+x}
total_value = (items.collect{|i| i.value}).inject{|sum, x| sum+x}
puts "Total items: #{items.size}"
puts "Total weight: #{total_weight}"
puts "Total value: #{total_value}"
puts ""
puts "Searching for knapsack of weight #{weight}"
puts "Population size #{pop_size}"
puts "Max generations #{generations}"
puts "Crossover rate #{crossover}"
puts "Mutation rate #{mutation}"
genetic.run(items , weight)
