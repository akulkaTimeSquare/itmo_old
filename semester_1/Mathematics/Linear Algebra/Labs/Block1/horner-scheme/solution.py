def computing(n, coefs, x):
    if not x0: return coefs[-1]

    r = 0
    for a in coefs:
        r *= x
        r += a
    
    return r


def main():
    with open("input.txt", "r") as fr:
        n = int(fr.readline().strip())
        coefs = [int(a) for a in fr.readline().strip().split(" ")]
        x0 = int(fr.readline().strip())

    result = computing(n, coefs, x0)

    with open("output.txt", "w") as fw:
        fw.write(str(result))


if __name__ == "__main__":
    main()