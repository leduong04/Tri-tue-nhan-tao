board = [['_' for i in range(3)] for i in range(3)]


# board[0] = ['x' for i in range(3)]
# board[0][2] = 'o'
def print_board():
    for i in board:
        print(" | ", end="")
        for j in i:
            print(j, end=" | ")
        print("\n")


def checkWin():
    # if board[0][0] == board[0][1] == board[0][2] or board[0][0] == board
    for i in range(3):
        if board[i][0] == board[i][1] == board[i][2] and board[i][0] != '_':
            return board[i][0]

    for i in range(3):
        if board[0][i] == board[1][i] == board[2][i] and board[0][i] != '_':
            return board[0][i]

    if board[1][1] == board[2][2] == board[0][0] and board[1][1] != '_':
        return board[1][1]
    if board[2][0] == board[1][1] == board[0][2] and board[1][1] != '_':
        return board[1][1]
    count_ = 0
    for i in board:
        for j in i:
            if j == '_':
                count_ += 1
    if count_ == 0:
        return "Tie"


# if checkWin():
#     print(checkWin())


def PlayI():
    while True:
        # x, y = list(map(int, input("Nhap vi tri: ").split()))
        x = int(input("Nhap x: "))
        y = int(input("Nhap y: "))

        # print(x, y)
        if (x < 3 and y < 3 and x >= 0 and y >= 0 and board[x][y] == '_'):
            board[x][y] = 'x'
            break


# PlayI()
# print_board()
def mainLoof():
    # best = float('-inf')
    a, b = -888, -888
    while True:
        print_board()
        if checkWin():
            if checkWin() != "Tie":
                print("\n", checkWin(), "WON")
            else:
                print("\nTie")
            break
        # else:
        print("Yout turn: ")
        PlayI()

        print_board()
        if checkWin():
            if checkWin() != "Tie":
                print("\n", checkWin(), "WON")
            else:
                print("\nTie")
            break

        print("computer s' turn: ")
        best = float('-inf')
        for i in range(3):
            for j in range(3):
                if board[i][j] == '_':
                    board[i][j] = 'o'
                    score = minimax("min")
                    board[i][j] = '_'
                    # best = max(score, best)
                    # a, b = i, j
                    if score > best:
                        best = score
                        a, b = i, j
        board[a][b] = 'o'


winer = {'x': -10, 'o': 10, 'Tie': 0}


def minimax(x):
    if checkWin():
        return winer[checkWin()]

    if x == "max":
        best = float('-inf')

        for i in range(3):
            for j in range(3):
                if board[i][j] == '_':
                    board[i][j] = 'o'
                    score = minimax("min")
                    board[i][j] = '_'
                    best = max(score, best)
        return best

    if x == "min":
        best = float('inf')

        for i in range(3):
            for j in range(3):
                if board[i][j] == '_':
                    board[i][j] = 'x'
                    score = minimax("max")
                    board[i][j] = '_'
                    best = min(score, best)
        return best


mainLoof()
