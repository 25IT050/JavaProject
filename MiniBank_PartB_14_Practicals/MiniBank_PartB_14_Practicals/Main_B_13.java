import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.*;

public class Main_B_13 {
    @Target(ElementType.FIELD) @Retention(RetentionPolicy.RUNTIME) @interface Id {}
    static class Account {
        @Id private String accountNumber; private String ownerName; private long balance;
        Account(String n,String o,long b) { accountNumber=n; ownerName=o; balance=b; }
        @Override public String toString() { return accountNumber+" | "+ownerName+" | "+balance; }
    }
    static class Repository<T> {
        private final HashMap<String,T> data=new HashMap<>();
        private String findId(T item) {
            for(Field field:item.getClass().getDeclaredFields()) if(field.isAnnotationPresent(Id.class)) {
                try { field.setAccessible(true); return String.valueOf(field.get(item)); }
                catch(IllegalAccessException e) { throw new IllegalStateException(e); }
            }
            throw new IllegalArgumentException("Class must have a field marked @Id");
        }
        void save(T item) { data.put(findId(item),item); }
        @SafeVarargs final void saveAll(T... items) { for(T item:items) save(item); }
        T findById(String id) { return data.get(id); }
        List<T> findAll() { return new ArrayList<>(data.values()); }
        void delete(String id) { data.remove(id); }
    }
    static <N extends Number> double average(N[] values) {
        if(values.length==0) return 0;
        double total=0; for(N value:values) total+=value.doubleValue();
        return total/values.length;
    }
    public static void main(String[] args) {
        Repository<Account> repo=new Repository<>();
        repo.saveAll(new Account("AC0001","Riya",5000),new Account("AC0002","Aarav",2000),new Account("AC0003","Neha",9000));
        System.out.println("Find AC0002: "+repo.findById("AC0002"));
        System.out.println("All accounts:");
        for(Account a:repo.findAll()) System.out.println(a);
        Integer[] marks={70,80,90};
        System.out.println("Average marks: "+average(marks));
        repo.delete("AC0002");
        System.out.println("Count after delete: "+repo.findAll().size());
    }
}
