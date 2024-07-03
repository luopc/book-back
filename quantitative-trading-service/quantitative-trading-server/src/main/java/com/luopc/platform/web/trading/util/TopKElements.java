package com.luopc.platform.web.trading.util;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class TopKElements {

    public static List<Double> findTopKElements(List<Double> nums, int k) {
        if (nums == null || nums.isEmpty() || k <= 0) {
            return new ArrayList<>();
        }

        PriorityQueue<Double> maxHeap = new PriorityQueue<>(Double::compareTo); // 最大堆
        for (Double num : nums) {
            if (maxHeap.size() < k) {
                maxHeap.offer(num);
            } else if (maxHeap.peek() < num) {
                maxHeap.poll();
                maxHeap.offer(num);
            }
        }

        List<Double> result = new ArrayList<>();
        while (!maxHeap.isEmpty()) {
            result.add(maxHeap.poll());
        }

        // 因为是从最大堆中取出的元素，所以结果列表是降序的。如果需要升序，可以反转列表。
        Collections.reverse(result);

        return result;
    }

    public static void main(String[] args) {
        List<Double> nums = Arrays.asList(60d, 23d, 3d, 2d, 1d, 5d, 6d, 4d, 8d, 9d, 7d, 12d);
        int k = 5; // 假设列表中的元素足够多，以展示方法

        // 注意：在实际应用中，你可能需要确保k不大于列表的长度
        List<Double> topK = findTopKElements(nums, Math.min(k, nums.size()));
        System.out.println(topK);
    }
}