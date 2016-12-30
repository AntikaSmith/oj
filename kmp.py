def naiveLoop(pattern, src):
	"""
	@pattern short pattern string with length k
	@src long source string with length n
	@return matching start index if succeed or -1 if it's failed
	naive loop algorithm has a complexity of O(k*n)
	"""
	if not pattern:
		return 0
	else:
		i, j = 0, 0
		while(i < len(src)):
			while(j < len(pattern) and i + j < len(src)):
				if pattern[j] == src[i + j] and j == len(pattern) - 1:
					return i
				elif pattern[j] == src[i + j]:
					j += 1
				else:
					j = 0
					break
			i += 1
		return -1

def builNextArray(pattern):
	"""
	build the nextArray nextArray of the pattern string
	"""
	from array import array
	nextArray = array('L', (0,) * len(pattern))
	j = 0
	for i in range(1, len(pattern)):
		while (j > 0 and pattern[j ] != pattern[i]):
			j = nextArray[j]
		if pattern[i] == pattern[j]:
			j = j + 1
		nextArray[i] = j

	return nextArray

def kmp(pattern, src):
	"""
	@pattern short pattern string
	@src long source string
	@return  matching start index if succeed or -1 if it's failed
	"""
	nextArray = builNextArray(pattern)
	if not pattern:
		return 0
	j = 0
	for i in range(0, len(src)):
		while(src[i] != pattern[j ] and j > 0):
			j = nextArray[j]
		if src[i] == pattern[j]:
			j += 1
		if j == len(pattern):
			return i - len(pattern) + 1
	return -1

def test(strMatch):
	assert strMatch("", "") == 0
	assert strMatch("", "foo") == 0
	assert strMatch("1234", "") == -1
	assert strMatch("123", "21233") == 1
	assert strMatch("123", "121233") == 2
	assert strMatch("ha?", "123") == -1
	assert strMatch("ABABAC", "ABABABCABABABCABABABC") = -1
	print("congratulations, the function works correctly!")

test(naiveLoop)
test(kmp)
