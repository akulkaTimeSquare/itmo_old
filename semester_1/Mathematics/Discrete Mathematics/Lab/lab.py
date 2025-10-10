import sys
from collections import Counter
from collections import deque


class Node:
    def __init__(self, name, left, right, data):
        self.name = name
        self.left = left
        self.right = right
        self.data = data


def make_node(name=None, left=None, right=None, data=0):
    return Node(name, left, right, data)


def encoding(input_name, output_name):
    try:
        input_file = open(input_name, "r")
    except FileExistsError or FileNotFoundError or NameError:
        return -1
    try:
        output_file = open(output_name, "w")
    except FileExistsError or FileNotFoundError or NameError:
        return -2

    text = ""
    count = Counter()
    for line in input_file:
        text += line
        for char in line:
            count[char] += 1
    chars = [make_node(name=name, data=data) for name, data in count.most_common()]

    while len(chars) != 1:
        left = chars.pop(-2)
        right = chars.pop(-1)
        new_node = make_node(left.name + right.name, left, right, left.data + right.data)
        chars.append(new_node)
        chars.sort(key=lambda x: (x.data, x.name), reverse=True)

    dictionary = {}
    stack = deque([chars[0]])
    cur = ""
    cur_node = stack[0]
    last_node = None
    while stack:
        if cur_node.left is None:
            dictionary[cur_node.name] = cur
            cur_node, last_node = last_node, cur_node
            cur = cur[:-1]
        elif last_node not in (cur_node.left, cur_node.right):
            if last_node is not None:
                stack.appendleft(cur_node)
            cur_node, last_node = cur_node.left, cur_node
            cur += "0"
        elif cur_node.right is not last_node:
            cur_node, last_node = cur_node.right, cur_node
            cur += "1"
        else:
            last_node = stack.popleft()
            if stack:
                cur_node = stack[0]
            cur = cur[:-1]
    btext = ""
    for char in text:
        btext += dictionary[char]

    output_file.write(chr(len(count)))
    output_file.write("\n")
    for element in dictionary:
        output_file.write(chr(ord(element)+45))
        output_file.write(chr(int("1"+dictionary[element], 2)))
        output_file.write("\n")

    for i in range(0, len(btext), 6):
        output_file.write(chr(int("1" + btext[i:i+6], 2)))

    input_file.close()
    output_file.close()

    return 0


def decoding(input_name, output_name):
    try:
        input_file = open(input_name, "r")
    except FileExistsError or FileNotFoundError or NameError:
        return -1
    try:
        output_file = open(output_name, "w")
    except FileExistsError or FileNotFoundError or NameError:
        return -2
    """
    text = ""
    for line in input_file:
        text += line
    text = text.split(" !!! ")
    nodes = text[1]
    """
    count = ord(input_file.readline()[:-1])
    nodes = []
    for i in range(count):
        line = input_file.readline()[:-1]
        name, val = chr(ord(line[0])-45), bin(ord(line[1]))[3:]
        nodes.append((name, val))
    root = make_node()
    for name, val in nodes:
        cur_node = root
        for j in val:
            if j == "0":
                if cur_node.left is None:
                    cur_node.left = make_node()
                cur_node = cur_node.left
            else:
                if cur_node.right is None:
                    cur_node.right = make_node()
                cur_node = cur_node.right
        cur_node.val = val[-1]
        cur_node.name = name

    text = input_file.readline()[:-1]
    cur_node = root
    btext = ""
    for i in text:
        i = bin(ord(i))[3:]
        btext += i
    text = btext

    decoded_text = ""
    for i in text:
        if i == "0":
            cur_node = cur_node.left
        else:
            cur_node = cur_node.right
        if cur_node.left is None:
            decoded_text += cur_node.name
            cur_node = root

    output_file.write(decoded_text)
    input_file.close()
    output_file.close()

    return 0


if __name__ == "__main__":
    args = sys.argv
    if len(args) != 4:
        print("Wrong arguments")
        exit()

    if args[1] == "--decode":
        res = decoding(args[2], args[3])
    elif args[1] == "--encode":
        res = encoding(args[2], args[3])
    else:
        print("Wrong arguments")
        exit()

    if -1 == res:
        print("Input file name error")
    elif -2 == res:
        print("Output file name error")
    elif 0 == res:
        print("Work have done!")
