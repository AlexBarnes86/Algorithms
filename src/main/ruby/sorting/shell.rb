#!/usr/bin/ruby

puts "Not Implemented"
exit(0)

ARGV.collect! {|x| x.to_i}

inc = ((ARGV.size+0.5)/2).to_i #round
while inc > 0 do
	for i in inc..ARGV.size
		temp = ARGV[i]
		j = i
		while j >= inc and ARGV[j-inc] > temp do
			ARGV[j] = ARGV[j-inc]
			j = j-inc
		end
		ARGV[j] = temp
	end
	inc = ((inc+0.5)/2.2).to_i
end

puts ARGV.join(', ')
