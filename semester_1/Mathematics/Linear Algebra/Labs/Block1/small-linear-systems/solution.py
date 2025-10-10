def calc_det_2x2(m):
    return m[0][0] * m[1][1] - m[0][1] * m[1][0]


def computing(coefs, fcs):
    det_coefs = calc_det_2x2(coefs)
    det1 = calc_det_2x2([[fcs[0], coefs[0][1]], [fcs[1], coefs[1][1]]])
    det2 = calc_det_2x2([[coefs[0][0], fcs[0]], [coefs[1][0], fcs[1]]])
    
    if det_coefs:   
        x = det1 / det_coefs
        y = det2 / det_coefs
        return (2, (x, y))
    
    if det1 != 0 or det2 != 0: #отсутствие кратности
        return (0, )

    if (coefs[0][0] == 0 and coefs[0][1] == 0 and coefs[1][0] == 0 \
                                 and coefs[1][1] == 0): #все нули, отлов отдельного случая кратности
        return (5, ) if fcs[0] == 0 and fcs[1] == 0 else (0, )

    if (coefs[0][0] == 0 and coefs[1][0] == 0): #y exists
        return (4, fcs[0] / coefs[0][1]) if coefs[0][1] != 0 else (4, fcs[1] / coefs[1][1])
    
    if (coefs[0][1] == 0 and coefs[1][1] == 0): #x exists
        return (3, fcs[0] / coefs[0][0]) if coefs[0][0] != 0 else (3, fcs[1] / coefs[1][0])
    
    #kx + b
    return (1, (- coefs[0][0] / coefs[0][1], fcs[0] / coefs[0][1])) \
        if coefs[0][1] != 0 else (1, (- coefs[1][0] / coefs[1][1], fcs[1] / coefs[1][1]))


def main():
    with open("input.txt", "r") as fr:
        a, b, c, d, p, q = map(float, fr.readline().rstrip().split(" "))
    
    coefs = [[a, b], [c, d]]
    fcs = [p, q]
    result = computing(coefs, fcs)

    with open("output.txt", "w") as fw:
        if result[0] in (0, 5): fw.write(str(result[0]))
        elif result[0] in (1, 2): fw.write(str(result[0]) + " " + str(result[1][0]) + " " + str(result[1][1]))
        else: fw.write(str(result[0]) + " " + str(result[1]))


if __name__ == "__main__":
    main()
