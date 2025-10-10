def find_size(a):
    return (len(a), len(a[0]))


def addition(a, b):
    na, ma = find_size(a)
    nb, mb = find_size(b)
    if na != nb or ma != mb: return -1

    matrix = [[0] * ma for _ in range(na)]
    for i in range(na):
        for j in range(ma):
            matrix[i][j] = a[i][j] + b[i][j]
    return matrix


def transpose(a):
    na, ma = find_size(a)
    matrix = [[0] * na for _ in range(ma)]
    for i in range(ma):
        for j in range(na):
            matrix[i][j] = a[j][i]
    return matrix


def multiply_number_and_matrix(a, b):
    nb, mb = find_size(b) 
    matrix = [[0] * mb for i in range(nb)]
    for i in range(nb):
        for j in range(mb):
            matrix[i][j] = a * b[i][j]
    return matrix


def get_col(a, j):
    return [a[k][j] for k in range(len(a))]


def find_sum(a, b):
    return sum(a[i] * b[i] for i in range(len(a)))


def multiply_two_matrix(a, b):
    na, ma = find_size(a)
    nb, mb = find_size(b)
    if ma != nb: return -1

    matrix = [[0] * mb for _ in range(na)]
    for i in range(na):
        for j in range(mb):
            matrix[i][j] = find_sum(a[i], get_col(b, j))
    return matrix


def print_matrix(a):
    for i in a:
        for j in i:
            print(j, end = " ")
        print()


def read_matrix_from_file(file):
    n, m = map(int, file.readline().strip().split())
    data = map(float, file.readline().strip().split())
    matrix = [[0] * m for _ in range(n)]
    for i in range(n):
        for j in range(m):
            matrix[i][j] = next(data)
    return matrix


def computing(a, alph, b, c):
    m = addition(multiply_number_and_matrix(alph, b), transpose(c))
    if m == -1: return 0, (0, 0), [[0]]
    m = multiply_two_matrix(a, m)
    if m == -1: return 0, (0, 0), [[0]]
    return (1, find_size(m), m)


def main():
    with open("input.txt", "r") as fr:
        a = read_matrix_from_file(fr) 
        b = read_matrix_from_file(fr)
        c = read_matrix_from_file(fr)
        alph = float(fr.readline().strip())
        
        """
        print_matrix(a)
        print()
        print_matrix(b)
        print()
        print_matrix(c)
        print()
        print(alph)
        """
    result = computing(a, alph, b, c)

    with open("output.txt", "w") as fw:
        s = ""
        for i in result[2]:
            for j in i:
                s += str(j) + " "
        text = [str(result[0]) + "\n",
                str(result[1][0]) + " " + str(result[1][1]) + "\n",
                s.rstrip()]
        fw.writelines(text)

if __name__ == "__main__":
    main()
    """
    a = [[1, 2, 3],
         [5, 5, 6],
         [7, 8, 9]]
    print("matrix a:")
    print_matrix(a)
    print("\nsize:")
    print(find_size(a))
    print("\ntransposition:")
    print_matrix(transpose(a))
    
    print("-" * 15)
    b = [[1, 2, 3],
         [5, 6, 7],
         [8, 9, 9]]
    print("matrix b:")
    print_matrix(b)
    print("\nsize:")
    print(find_size(b))
    print("\naddition of a and b:")
    print_matrix(addition(a, b))
    print("\nmultiplying of a and b:")
    print_matrix(multiply_two_matrix(a, b))
    
    print("-" * 15)
    alph = 12
    print("number:", alph)
    print("\nmultiplying of number and b:")
    print_matrix(multiply_number_and_matrix(alph, b))
    print("\nmultiplying of number and a:")
    print_matrix(multiply_number_and_matrix(alph, a))
    """