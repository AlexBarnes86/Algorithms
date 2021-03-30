#!/usr/bin/ruby

data = []
ARGV[0].to_i.times do
	data << rand(ARGV[1].to_i)
end

print data.join(' ')
