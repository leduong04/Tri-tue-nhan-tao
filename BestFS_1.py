import pygame
import math
import time

stop = 0
time_run = 0
sleep_times = 0
pa = ""


n = 10
DoThi = {
    'A': {'D', 'F'},
    'B': {},
    'C': {},
    'D': {'E', 'H'},
    'E': {'A', 'K', 'I'},
    'F': {'E', 'G'},
    'G': {},
    'H': {'C', 'K'},
    'I': {'B', 'F', 'K'},
    'K': {'B'},
}
TrongSo = {
    'A': [14,0],
    'B': [0,0],
    'C': [15,0],
    'D': [6,0],
    'E': [8,0],
    'F': [7,0],
    'G': [12,0],
    'H': [10,0],
    'I': [4,0],
    'K': [2,0],
}
begin = 'A'
end = 'B'



# Khởi tạo thư viện Pygame
pygame.init()


# Thiết lập cửa sổ
WIDTH, HEIGHT = 800, 600
screen = pygame.display.set_mode((WIDTH, HEIGHT))
pygame.display.set_caption("Thuật toán Tìm kiếm tốt nhất đầu tiên")



TapDinh = list(DoThi.keys())
if begin not in TapDinh:
    print(f"{begin} khong co trong do thi")

else:

    start_time = time.time()

    OPEN = [[TrongSo[begin], begin, None]]
    DaDuyet = {begin: None}

    print("\nQua trinh duyet: \n")
    print("Duyet | OPEN  ([10, 'C', 'A']: 10 la trong so, 'C' la ten dinh, 'A' la dinh cha)")


# Tạo danh sách chưa thông tin về mỗi nút
    nodes = {}

# Tạo danh sách chứa thông tin đường nối
    edges = []


    center_x, center_y = WIDTH // 2, HEIGHT // 2
    radius = min(WIDTH, HEIGHT) // 3

    for i in range(n):
        label = TapDinh[i]

        #Tính toán để cho hình tạo ra bởi các điểm là một hình đa giác đều
        angle = (2 * math.pi / n) * i
        x = center_x + radius * math.cos(angle)
        y = center_y + radius * math.sin(angle)


        color = (50, 100, 0)
        weight = TrongSo[TapDinh[i]][0]

        neighbors = DoThi[TapDinh[i]]

        nodes[label] = {"x": x, "y": y, "label": label, "weight": weight, "color": color}
        for neighbor in neighbors:
            edges.append((label, neighbor))

    running = True
    while running:
        # duyệt qua từng sự kiện
        for event in pygame.event.get():
            # bắt sự kiện QUIT
            if event.type == pygame.QUIT:
                running = False

        while len(OPEN) and len(pa) == 0:

            trongsohientai, dinhhientai, dad = OPEN.pop(0)
            nodes[dinhhientai]["color"] = (255, 0, 0)
            time.sleep(1)
            sleep_times += 1

            screen.fill((255, 255, 255))

            # Vẽ các đường đi
            for edge in edges:
                start_node = nodes[edge[0]]
                end_node = nodes[edge[1]]

                pygame.draw.line(screen, (0, 0, 0), (start_node["x"], start_node["y"]), (end_node["x"], end_node["y"]),
                                 2)

                angle = math.atan2(end_node["y"] - start_node["y"], end_node["x"] - start_node["x"])
                arrow_length = 20

                arrow_x = end_node["x"] - arrow_length * math.cos(angle)
                arrow_y = end_node["y"] - arrow_length * math.sin(angle)

                pygame.draw.polygon(screen, (0, 0, 0), [
                    (arrow_x, arrow_y),
                    (arrow_x + 10 * math.cos(angle - math.pi + math.pi / 6),
                     arrow_y + 10 * math.sin(angle - math.pi + math.pi / 6)),
                    (arrow_x + 10 * math.cos(angle - math.pi - math.pi / 6),
                     arrow_y + 10 * math.sin(angle - math.pi - math.pi / 6))

                ])

            # Vẽ đỉnh và in tên đỉnh
            for node_info in nodes.values():
                pygame.draw.circle(screen, node_info["color"], (node_info["x"], node_info["y"]), 10)

                # In tên của đỉnh
                text = f"{node_info['label']} {node_info['weight']}"
                font = pygame.font.Font(None, 36)
                text_surface = font.render(text, True, (0, 0, 0))
                text_rect = text_surface.get_rect()
                text_rect.center = (node_info["x"], node_info["y"] - 20)
                screen.blit(text_surface, text_rect)

                pygame.draw.circle(screen, (255, 0, 0), (30, 50), 10)
                font = pygame.font.Font(None, 24)
                note_text = font.render("Da duyet", True, (0, 0, 0))
                note_rect = note_text.get_rect()
                note_rect.topleft = (50, 40)
                screen.blit(note_text, note_rect)

                pygame.draw.circle(screen, (50, 100, 0), (30, 100), 10)
                note_text = font.render("Chua duyet", True, (0, 0, 0))
                note_rect = note_text.get_rect()
                note_rect.topleft = (50, 90)
                screen.blit(note_text, note_rect)

                PS = pygame.font.Font(None, 36)
                PS_text = font.render("Xem chi tiet thu tu duyet trong terminal", True, (0, 0, 0))
                PS_rect = PS_text.get_rect()
                PS_rect.center = (150, 10)
                screen.blit(PS_text, PS_rect)
            # Chập nhật nội dung cửa sổ
            pygame.display.flip()

            if TrongSo[dinhhientai][1] == 0:
                DaDuyet[dinhhientai] = dad
                TrongSo[dinhhientai][1] = 1

                print(dinhhientai, end="     | ")
                if dinhhientai == end:
                    end_time = time.time()

                    time_run = end_time - start_time

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
            Path = DaDuyet
        else:
            Path = {}

        if Path == {}:

            for node_info in nodes.values():
                pygame.draw.circle(screen, node_info["color"], (node_info["x"], node_info["y"]), 10)

                text = f"{node_info['label']} {node_info['weight']}"
                font = pygame.font.Font(None, 36)
                text_surface = font.render(text, True, (0, 0, 0))
                text_rect = text_surface.get_rect()
                text_rect.center = (node_info["x"], node_info["y"] - 20)
                screen.blit(text_surface, text_rect)

                cost_font = pygame.font.Font(None, 36)
                cost_text = cost_font.render("Khong tim thay duong di tu " + begin + " den " + end, True, (0, 0, 0))
                cost_rect = cost_text.get_rect()
                cost_rect.center = (WIDTH // 2, HEIGHT - 40)
                screen.blit(cost_text, cost_rect)

                pygame.display.flip()
            if stop == 0:
                print(f"Khong tim thay {end}")
                stop = 1
        else:
            if stop == 0:
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

                print("\n", pa)
                print(f"Tong chi phi la {sum}")
                stop = 1

            if len(pa):
                points = []
                for i in p:
                    points.append([nodes[i]["x"], nodes[i]["y"]])

                for j in range(len(points) - 1):
                    pygame.draw.line(screen, (255, 0, 0), points[j], points[j + 1], 5)

            for node_info in nodes.values():
                pygame.draw.circle(screen, node_info["color"], (node_info["x"], node_info["y"]), 10)

                font = pygame.font.Font(None, 36)
                pa_text = font.render("Duong di tu " + begin + " den " + end + ": " + pa, True, (0, 0, 0))
                pa_rect = pa_text.get_rect()
                pa_rect.center = (WIDTH // 2, HEIGHT - 80)
                screen.blit(pa_text, pa_rect)

                cost_font = pygame.font.Font(None, 36)
                cost_text = cost_font.render("Chi phi: " + str(sum), True, (0, 0, 0))
                cost_rect = cost_text.get_rect()
                cost_rect.center = (WIDTH // 2, HEIGHT - 40)
                screen.blit(cost_text, cost_rect)

                elapsed_time = time_run - sleep_times
                time_font = pygame.font.Font(None, 36)
                time_text = time_font.render("Thoi gian chay: " + str(round(elapsed_time, 3)) + " giay", True,
                                             (0, 0, 0))
                time_rect = time_text.get_rect()
                time_rect.center = (WIDTH // 2, HEIGHT - 10)
                screen.blit(time_text, time_rect)

            pygame.display.flip()

    pygame.quit()
