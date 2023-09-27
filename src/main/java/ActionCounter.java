import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

class ActionCounter {
    // Очередь для хранения временных меток обращений
    private final Deque<Integer> queue; // O(n) памяти, n - кол-во обращений в последние 5 минут

    // HashMap для подсчета обращений в каждый момент времени
    private final Map<Integer, Integer> countMap; // O(n) памяти, n - количество различных временных меток в последние 5 минут

    public ActionCounter() {
        queue = new ArrayDeque<>();
        countMap = new HashMap<>();
    }

    public void call(int timestamp) { // O(n), где n количество устаревших элементов
        // Добавляем обращение в очередь и увеличиваем счетчик для данной временной метки
        queue.addLast(timestamp);
        countMap.put(timestamp, countMap.getOrDefault(timestamp, 0) + 1);

        // Удаляем устаревшие обращения из очереди и счетчика
        while (!queue.isEmpty() && timestamp - queue.getFirst() >= 300) {
            int oldTimestamp = queue.removeFirst();
            countMap.put(oldTimestamp, countMap.get(oldTimestamp) - 1);
            if (countMap.get(oldTimestamp) == 0) {
                countMap.remove(oldTimestamp);
            }
        }
    }

    public int getActions(int timestamp) { // O(k) где k - количество различных временных меток в countMap
        int count = 0;
        for (int time : countMap.keySet()) {
            if (timestamp - time < 300) {
                count += countMap.get(time);
            }
        }
        return count;
    }
}
