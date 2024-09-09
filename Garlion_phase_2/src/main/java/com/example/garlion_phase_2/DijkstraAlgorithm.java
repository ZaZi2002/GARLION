package com.example.garlion_phase_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class DijkstraAlgorithm {

    public static int distance(int start, int end) {
        int[][] graph = readGraphFromTxt("graph.txt");
        int[] distances = dijkstra(graph, start);
        return distances[end];
    }

    public static void showMap(){
        int[][] graph = readGraphFromTxt("graph.txt");
        for (int i=1;i< graph[1].length;i++){
            System.out.println(graph[i][0] + " " + graph[i][1]);
        }
    }

    public static int[][] readGraphFromTxt(String filename) {
        int[][] graph = null;
        try {
            Scanner scanner = new Scanner(new File(filename));
            int nodes = scanner.nextInt();
            int yal = scanner.nextInt();
            graph = new int[yal][3];
            for (int i = 0; i < yal; i++) {
                for (int j = 0; j < 3; j++) {
                    graph[i][j] = scanner.nextInt();
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return graph;
    }
    public static int[] dijkstra(int[][] graph, int start) {
        int n = graph.length;
        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start] = 0;
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int u = node.vertex;
            int distanceU = node.distance;

            if (distanceU > distances[u]) {
                continue;
            }

            for (int i = 0; i < graph.length; i++) {
                if (graph[i][0] == u) {
                    int v = graph[i][1];
                    int distanceUV = graph[i][2];
                    int totalDistanceV = distances[u] + distanceUV;

                    if (totalDistanceV < distances[v]) {
                        distances[v] = totalDistanceV;
                        queue.add(new Node(v, distances[v]));
                    }
                }
            }
        }

        return distances;
    }

    public static String getPath(int[] distances, int start, int end, int[][] graph) {
        StringBuilder path = new StringBuilder();
        int current = end;
        path.append(current);

        while (current != start) {
            for (int i = 0; i < graph.length; i++) {
                if (graph[i][1] == current && distances[current] == distances[graph[i][0]] + graph[i][2]) {
                    current = graph[i][0];
                    path.insert(0, current + " -> ");
                    break;
                }
            }
        }

        return path.toString();
    }

    private static class Node implements Comparable<Node> {
        private int vertex;
        private int distance;

        public Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        public int getVertex() {
            return vertex;
        }

        public int getDistance() {
            return distance;
        }

        @Override
        public int compareTo(Node node) {
            return Integer.compare(distance, node.distance);
        }
    }

}