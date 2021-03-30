#!/usr/bin/ruby

ARGV.collect! {|x| x.to_i}

for i in 1..ARGV.size-1 do
	for j in 0..i do
		break if ARGV[i] < ARGV[j]
	end
	j.upto(ARGV.size-2) do |x|
		ARGV[x], ARGV[x+1] = ARGV[x+1], ARGV[x]
	end
end

puts ARGV.join(', ')
