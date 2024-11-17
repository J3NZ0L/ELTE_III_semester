︠fbc292db-41d4-4509-b6ed-e62f3c648370s︠
def is_two_digit(num):
    return len(set(str(num))) <= 2

def d(n):
    m = 1
    while True:
        possible_answer = n * m
        if is_two_digit(possible_answer):
            return possible_answer
        m+=1

def sum_dn_upto(n):
    total = sum(d(k) for k in range(1, n+1))
    return total

n = 20
sum_dn_upto(n)
︡3e5ecd8b-a7f8-4605-a010-dc45deb1e096︡{"stdout":"210\n"}︡{"done":true}
︠3af610e0-4b12-4448-a230-bf01fabb7cb9︠









