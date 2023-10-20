
def BFS():
    while len(OPEN):
        trongsohientai, dinhhientai, dad = OPEN.pop(0)
        if TrongSo[dinhhientai][1] == 0:
            DaDuyet[dinhhientai] = dad
            TrongSo[dinhhientai][1] = 1
            print(dinhhientai, end="     | ")
            if dinhhientai == end:
                break
            else:
                for i in DoThi[dinhhientai]:
                    if TrongSo[i][1] == 0:
                        k = -1
                        for j in range(len(OPEN)):
                            if OPEN[j][1] == i:
                                k = j
                        if k != -1:
                            OPEN.remove(OPEN[k])
                        OPEN.append([TrongSo[i][0], i, dinhhientai])
                OPEN.sort()
            print(OPEN)
    if end in DaDuyet:
        return DaDuyet
    else:
        return {}



n = 10
n = 8
DoThi = {
    'A': ['B', 'C', 'D'],
    'B': ['A', 'E'],
    'C': ['A', 'E', 'F'],
    'D': ['A', 'H'],
    'E': ['B', 'C', 'G'],
    'F': ['C', 'G'],
    'G': ['E', 'F', 'H'],
    'H': ['D', 'G'],

}
TrongSo = {
    'A': [30, 0],
    'B': [20, 0],
    'C': [10, 0],
    'D': [25, 0],
    'E': [13, 0],
    'F': [15, 0],
    'G': [18, 0],
    'H': [0, 0],
}
begin = 'A'
end = 'H'





OPEN = [[TrongSo[begin], begin, None]]
DaDuyet = {begin: None}
print("\nQua trinh duyet: \n")
print("Duyet | OPEN  ([10, 'C', 'A']: 10 la trong so, 'C' la ten dinh, 'A' la dinh cha)")
Path = BFS()
if Path == {}:
    print(f"Khong tim thay {end}")
else:
    x = end
    p = [end]
    while True:
        p.append(Path[x])
        x = Path[x]
        if Path[x] == None:
            break
    pa = " <- ".join(p)
    sum = 0
    for i in p:
        sum += TrongSo[i][0]
    print("\nDuong di:", pa)
    print(f"Tong chi phi la {sum}")
