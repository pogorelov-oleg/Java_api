import java.util.*;

public class Peoples {

    private List<People> list;

    public Peoples() {
        this.list = new ArrayList<>();
    }

    public void add(String fullName) {
        this.list.add(new People(fullName));
    }

    /**
     * @apiNote Выводит повторяющиеся имена, в порядке убывания количества повторений
     */
    public void printCountFirstNames() {
        HashMap<String, Integer> map = new HashMap<>();
        for (People item: list) {
            map.putIfAbsent(item.firstName, 0);
            map.put(item.firstName, map.get(item.firstName) + 1);
        }
        List<CountName> list = new ArrayList<>();
        for (String key: map.keySet()) {
            list.add(new CountName(key, map.get(key)));
        }
//        list.sort((o1, o2) -> o2.count - o1.count);
        list.sort(CountName::compareTo);
        System.out.println("Повторящиеся имена:");
        for (CountName item : list) {
            if (item.count > 1) {
                System.out.printf("%s - %d\n", item.name, item.count);
            }
        }
    }

    class People {

        String firstName;
        String name;

        public People(String firstName, String name) {
            this.firstName = firstName;
            this.name = name;
        }

        public People(String fullName) {
            String[] partsName = fullName.split(" ");
            if (partsName.length > 0)
                this.firstName = partsName[0];
            if (partsName.length > 1)
                this.name = partsName[0];
        }

        @Override
        public String toString() {
            return "{" +
                    "firstName='" + firstName + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    class CountName implements Comparable<CountName>{

        String name;
        Integer count;

        public CountName(String name, int count) {
            this.name = name;
            this.count = count;
        }

        @Override
        public int compareTo(CountName o) {
            if (this.count == o.count)
                return this.name.compareTo(o.name);
            return - this.count.compareTo(o.count);
        }

        @Override
        public String toString() {
            return "{" +
                    "name='" + name + '\'' +
                    ", count=" + count +
                    '}';
        }
    }
}