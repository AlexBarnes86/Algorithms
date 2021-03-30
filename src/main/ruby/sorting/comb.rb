#!/usr/bin/ruby

ARGV.collect! {|x| x.to_i}

gap = ARGV.size
swaps = 0
until gap <= 1 and swaps == 0
	gap = (gap/1.25)
	i = 0
	swaps = 0
	until i + gap >= ARGV.size
		if ARGV[i] > ARGV[i+gap]
			ARGV[i], ARGV[i+gap] = ARGV[i+gap], ARGV[i]
			swaps += 1
		end
		i += 1
	end
end

puts ARGV.join(', ')
