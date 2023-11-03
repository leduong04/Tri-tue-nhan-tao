# from IPython.core.interactiveshell import dis

# import heapq



def dijkstra(graph, start, end, TrongSo):
    min = float('infinity')
    distances = {vertex: float('infinity') for vertex in graph}
    gs = {vertex: 0 for vertex in graph}
    distances[start] = 0
    # visited = set()
    previous_vertices = {}
    priority_queue = [[0, start]]

    solanduyet = 0

    while priority_queue:
        if solanduyet >= (2 ** len(graph)):
            return 0, []
        solanduyet += 1
        print("solanduyet: ", solanduyet)
        print("priority_queue", priority_queue)

        current_distance, current_vertex = priority_queue.pop(0)
        if current_distance > min:
            continue

        # print(f"current_distance {current_distance}, current_vertex {current_vertex} ")

        L = []

        for neighbor, weight in graph[current_vertex].items():

            # g = current_distance + weight
            g = gs[current_vertex] + weight
            f = g + TrongSo[neighbor]

            # print("f = ",f)
            #
            #
            # print("distances:  ", distances)
            # print("neighbor: distances[neighbor], distance: ", neighbor, ':', distances[neighbor], " > ", f)
            print(f"g({neighbor}) = {g}             f({neighbor}) = {f}")

            if f < distances[neighbor]:
                gs[neighbor] = g
                distances[neighbor] = f
                previous_vertices[neighbor] = current_vertex

            L.append((f, neighbor))

            # print("distances[end]: ", distances[end])
            min = distances[end]

        #     print("min: ", min)
        # print("L:", L)
        L.sort(reverse=True)
        for i in L:
            priority_queue.insert(0, i)

    path, current_vertex = [], end
    while current_vertex != start:
        path.insert(0, current_vertex)
        current_vertex = previous_vertices[current_vertex]
    path.insert(0, start)
    return distances[end], path


# graph = {
#     'S': {'A': 2, 'D': 3},
#     'A': {'S': 2, 'B': 3, 'D': 4},
#     'B': {'A': 3, 'E': 4, 'C': 3},
#     'C': {'B': 3},
#     'D': {'S': 3, 'A': 4, 'E': 1},
#     'E': {'B': 4, 'D': 6, 'F': 3},
#     'F': {'E': 3, 'G': 2},
#     'G': {'F': 2},
#
# }
#
# TrongSo = {
#     'A': 2,
#     'B': 3,
#     'C': 5,
#     'D': 6,
#     'E': 5,
#     'F': 1,
#     'G': 0,
#     'S': 8,
# }


graph = {
    'A': {'C': 5, 'D': 3, 'O': 4},
    'B': {'C': 4, 'E': 6, 'O':3 },
    'C': {'A': 5, 'B': 4, 'D': 2, 'E': 5, 'F': 2, 'O': 6},
    'D': {'A': 3, 'C': 2, 'F': 2, 'G': 4},
    'E': {'C': 5, 'F': 1, 'B': 6, 'H': 2, 'I': 5},
    'F': {'C': 2, 'D': 2, 'E': 1, 'G': 2, 'H': 5},
    'G': {'D': 4, 'F': 2, 'H': 2, 'T': 7},
    'H': {'E': 2, 'F': 5, 'T': 8, 'G': 2, 'I': 3},
    'I': {'E': 5,'H': 3, 'T': 4},
    'T': {'I': 4, 'H': 8, 'G': 7},
    'O': {'A': 4, 'B': 3, 'C': 6},
}

TrongSo = {
    'A': 0,
    'B': 0,
    'C': 0,
    'D': 0,
    'E': 0,
    'F': 0,
    'G': 0,
    'H': 0,
    'I': 0,
    # 'K': 0,
    'T': 0,
    'O': 0,
}



start_vertex = 'O'
end_vertex = 'T'

# print(len(graph))


shortest_distance, shortest_path = dijkstra(graph, start_vertex, end_vertex, TrongSo)
print(f"Đường đi ngắn nhất từ {start_vertex} đến {end_vertex} là {shortest_distance}")
print(f"Đường đi cụ thể: {' -> '.join(shortest_path)}")
