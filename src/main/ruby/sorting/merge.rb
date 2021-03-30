#!/usr/bin/ruby

ARGV.collect! {|x| x.to_i}

def merge(a, b)
	data = []
	i = j = 0
	until i == a.size or j == b.size
		if a[i] <= b[j]
			data << a[i]
			i+=1
		else
			data << b[j]
			j+=1
		end
	end
	if i == a.size
		until j == b.size
			data << b[j]
			j+=1
		end
	else
		until i == a.size
			data << a[i]
			i+=1
		end
	end
	data
end

def merge_sort ary
	if ary.size == 1
		return ary
	end
	left = merge_sort ary[0..ary.size/2-1]
	right = merge_sort ary[ary.size/2..ary.size-1]
	merge left, right
end

puts (merge_sort ARGV).join(', ')
