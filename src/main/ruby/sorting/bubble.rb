#!/usr/bin/ruby

ARGV.collect! {|x| x.to_i}

sorted = false
until sorted == true do
	sorted = true
	for i in 0..ARGV.size-2 do
		if ARGV[i] > ARGV[i+1]
			sorted = false
			temp = ARGV[i]
			ARGV[i] = ARGV[i+1]
			ARGV[i+1] = temp
		end
	end
end

puts ARGV.join(', ')
