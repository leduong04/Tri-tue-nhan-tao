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
    while priority_queue:

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


graph = {
    'A': {'D': 7, 'F': 20},
    'B': {},
    'C': {},
    'D': {'E': 4, 'H': 8},
    'E': {'A': 13, 'K': 4, 'I': 3},
    'F': {'E': 9, 'G': 4},
    'G': {},
    'H': {'C': 6, 'K': 5},
    'I': {'B': 5, 'F': 6, 'K': 9},
    'K': {'B': 6},
}

TrongSo = {
    'A': 14,
    'B': 0,
    'C': 15,
    'D': 6,
    'E': 8,
    'F': 7,
    'G': 12,
    'H': 10,
    'I': 4,
    'K': 2, }

start_vertex = 'A'
end_vertex = 'B'
shortest_distance, shortest_path = dijkstra(graph, start_vertex, end_vertex, TrongSo)
print(f"Đường đi ngắn nhất từ {start_vertex} đến {end_vertex} là {shortest_distance}")
print(f"Đường đi cụ thể: {' -> '.join(shortest_path)}")
