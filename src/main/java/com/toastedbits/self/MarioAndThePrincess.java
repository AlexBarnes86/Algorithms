package com.toastedbits.algorithms.self;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

import static java.util.stream.Collectors.toList;

/*
Here are our inputs:

We have N platforms, and for each platform, we have its (x, y) coordinate. This is a list of pairs.
We have M ladders, so for each ladder, we have a pair of platform ID's (i, j) that are connected by this ladder. This is a list of integer pairs, 0 <= i, j < N.
We also have a max distance, D, that Mario can jump, so if platforms are at (x1, y1) and (x2, y2), we cannot make the jump if |x1 - x2| > D or |y1 - y2| > D
The ID of Mario's platform, P

Our goal is to determine:

whether or not Mario can reach the Princess at all;
if he can, how long will it take him? (each jump or ladder climb costs a single unit of time, but we may omit this information)
Furthermore, if we also have Monster, starting from platform Q, can Mario reach the Princess before the Monster?

We are not concerned with diagonal jumps
 */
public class MarioAndThePrincess {
    public static void main(final String[] args) throws Exception {
        Scanner sc = new Scanner(MarioAndThePrincess.class.getResourceAsStream("input.txt"));

        List<Platform> platforms = loadPlatforms(sc);
        Platform princess = platforms.get(platforms.size()-1);
        Map<Integer, Set<Integer>> adjacencyList = loadLadders(sc, platforms.size());
        int distance = Integer.parseInt(sc.nextLine().trim());
        int marioIdx = Integer.parseInt(sc.nextLine());

        List<Platform> platformsX = new ArrayList<>();
        platformsX.addAll(platforms);
        platformsX.sort(Comparator.comparingInt(o -> o.x));

        List<Platform> platformsY = new ArrayList<>();
        platformsY.addAll(platforms);
        platformsY.sort(Comparator.comparingInt(o -> o.y));

        System.out.println("Sorted platformsX: " + platformsX);
        System.out.println("Sorted platformsY: " + platformsY);

        Platform solution = null;
        Set<Integer> visited = new HashSet<>();
        Queue<Platform> fringe = new LinkedList<>();
        fringe.add(platforms.get(marioIdx));
        while(!fringe.isEmpty()) {
            System.out.println("fringe = " + fringe);
            Platform platform = fringe.remove();

            if(platform.equals(princess)) {
                solution = platform;
                break;
            }

            if(visited.contains(platform.id)) {
                continue;
            }

            //add all adjacency entries
            adjacencyList.get(platform.id).forEach(idx -> {
                Platform p = platforms.get(idx);
                fringe.add(new Platform(p.id, p.x, p.y, platform));
            });

            fringe.addAll(findAdjacentImproved(platform, platformsX, platformsY, distance));
//            fringe.addAll(findAdjacentSuboptimal(platform, platforms, distance));

            visited.add(platform.id);
        }

        if(solution != null) {
            System.out.println("Solvable!");
            Platform cur = solution;
            List<Integer> path = new ArrayList<>();
            while(cur != null) {
                path.add(cur.id);
                cur = cur.parent;
            }
            Collections.reverse(path);
            System.out.println(path);
        }
        else {
            System.out.println("Unsolvable");
        }
    }

    private static class Platform {
        Platform parent;
        int id;
        int x, y;
        public Platform(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public Platform(int id, int x, int y, Platform parent) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.parent = parent;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Platform platform = (Platform) o;
            return id == platform.id &&
                x == platform.x &&
                y == platform.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, x, y);
        }

        @Override
        public String toString() {
            return "Platform{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                '}';
        }
    }

    private static List<Platform> findAdjacentSuboptimal(Platform platform, List<Platform> platforms, float distance) {
        List<Platform> adjacent = new ArrayList<>();
        //Suboptimal, we could instead binary search through platformsXY and platformsYX
        for(int i = 0; i < platforms.size(); ++i) {
            Platform jump = platforms.get(i);
            if(!jump.equals(platform) && (
                (jump.y == platform.y && Math.abs(jump.x - platform.x) <= distance) ||
                    (jump.x == platform.x && Math.abs(jump.y - platform.y) <= distance)
            )
            ) {
                adjacent.add(new Platform(jump.id, jump.x, jump.y, platform));
            }
        }
        return adjacent;
    }

    private static List<Platform> loadPlatforms(final Scanner sc) {
        List<Platform> platforms = new ArrayList<>();
        int numPlatforms = Integer.parseInt(sc.nextLine());
        for(int i = 0; i < numPlatforms; ++i) {
            final List<Integer> tokens = Arrays.stream(sc.nextLine().split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());
            Platform platform = new Platform(i, tokens.get(0), tokens.get(1));
            platforms.add(platform);
        }
        return platforms;
    }

    private static Map<Integer, Set<Integer>> loadLadders(final Scanner sc, int numPlatforms) {
        int M = Integer.parseInt(sc.nextLine());
        Map<Integer, Set<Integer>> adjacencyList = new HashMap<>();
        for (int i = numPlatforms - 1; i >= 0; i--) {
            adjacencyList.put(i, new HashSet<>());
        }
        for(int i = 0; i < M; ++i) {
            List<Integer> tokens = Arrays.stream(sc.nextLine().split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());
            adjacencyList.get(tokens.get(0)).add(tokens.get(1));
            adjacencyList.get(tokens.get(1)).add(tokens.get(0));
        }
        return adjacencyList;
    }

    private static List<Platform> findAdjacentImproved(Platform platform, List<Platform> platformsX, List<Platform> platformsY, float distance) {
        //Better - Use radius to find possible jumps
        List<Platform> adjacent = new ArrayList<>();

        //Add all horizontal jumps
        int sxId = Collections.binarySearch(platformsX, platform, Comparator.comparingInt(o -> o.x));
        int cur = sxId;
        while(cur >= 0) {
            Platform jump = platformsX.get(cur);
            if(platform.id != jump.id && jump.y == platform.y && Math.abs(platform.x - jump.x) <= distance) {
                adjacent.add(new Platform(jump.id, jump.x, jump.y, platform));
            }
            else if(platform.id != jump.id && Math.abs(platform.x - jump.x) > distance) {
                break;
            }
            cur--;
        }
        cur = sxId + 1;
        while(cur >= 0 && cur < platformsX.size()) {
            Platform jump = platformsX.get(cur);
            if(platform.id != jump.id && jump.y == platform.y && Math.abs(jump.x - platform.x) <= distance) {
                adjacent.add(new Platform(jump.id, jump.x, jump.y, platform));
            }
            else if(platform.id != jump.id && Math.abs(platform.x - jump.x) > distance) {
                break;
            }
            cur++;
        }

        //Add all vertical jumps
        int syId = Collections.binarySearch(platformsY, platform, Comparator.comparingInt(o -> o.y));
        cur = syId;
        while(cur >= 0) {
            Platform jump = platformsY.get(cur);
            if(platform.id != jump.id && jump.x == platform.x && Math.abs(platform.y - jump.y) <= distance) {
                adjacent.add(new Platform(jump.id, jump.x, jump.y, platform));
            }
            else if(platform.id != jump.id && Math.abs(platform.y - jump.y) > distance) {
                break;
            }
            cur--;
        }
        cur = syId + 1;
        while(cur >= 0 && cur < platformsY.size() - 1) {
            Platform jump = platformsY.get(cur);
            if(platform.id != jump.id && jump.x == platform.x && Math.abs(jump.y - platform.y) <= distance) {
                adjacent.add(new Platform(jump.id, jump.x, jump.y, platform));
            }
            else if(platform.id != jump.id && Math.abs(platform.y - jump.y) > distance) {
                break;
            }
            cur++;
        }

        return adjacent;
    }
}