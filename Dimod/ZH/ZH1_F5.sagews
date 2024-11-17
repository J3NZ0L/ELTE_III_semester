︠457abef5-a8d9-498a-aa46-4ed7740df4acs︠
def is_strong(n):
    factors = factor(n)
    for _, exp in factors:
        if exp < 2:
            return False
    return True

def is_perfect_power(n):
    if n < 2:
        return False
    # (not so efficient)
    for k in range(2, int(log(n, 2)) + 1):
        root = n^(1/k)
        if root.round()^k == n:
            return True
    return False

def is_achilles(n):
    return is_strong(n) and not is_perfect_power(n)

def strong_achilles_numbers(n):
    achilles_numbers = []
    for x in range(1, n):
        if is_achilles(x) and is_achilles(euler_phi(x)):
            achilles_numbers.append(x)
    return achilles_numbers

strong_achilles_numbers(10000)
︡db036dac-d97e-4e6d-b9fc-1806d7554b3e︡{"stdout":"[1, 500, 864, 1944, 2000, 2592, 3456, 5000]"}︡{"stdout":"\n"}︡{"done":true}









