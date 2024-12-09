︠8778b877-8f83-4b50-a374-839ae59fd32as︠
def sum_not_prime_results_of_f(n: int) -> int :
    p = lambda x: x^2 + x + 41
    res = 0
    for i in range(n + 1):
        y = p(i)
        if y not in Primes():
            res += y
    return res

print(sum_not_prime_results_of_f(100))
︡60711494-6925-4d25-b477-73a1e35539e0︡{"stdout":"75638\n"}︡{"done":true}
︠f84ccc3a-8802-4302-a3d3-7502a9480e96︠









