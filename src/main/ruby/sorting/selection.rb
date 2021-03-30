#!/usr/bin/ruby

ARGV.collect! {|x| x.to_i}

for i in 0..ARGV.size-1 do
	min = ARGV[i]
	min_idx = i
	for j in i..ARGV.size-1 do
		if(ARGV[j] < min)
			min = ARGV[j]
			min_idx = j
		end
	end
	ARGV[i], ARGV[min_idx] = ARGV[min_idx], ARGV[i]
end

puts ARGV.join(', ')
