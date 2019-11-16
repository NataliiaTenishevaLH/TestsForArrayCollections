import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertTimeout;


public class CustomerArrayTest {
    CustomerArray customerArray;

    public CustomerArrayTest(){
        customerArray = new CustomerArray();
        customerArray.add("One");
        customerArray.add("Two");
        customerArray.add("Three");
        customerArray.add("Four");
        customerArray.add("Five");
        customerArray.add("Six");
        customerArray.add("Seven");
        customerArray.add("Eight");
        customerArray.add("Nine");
    }

    @Test
    public void testAdd(){
        CustomerArray mock = mock(CustomerArray.class);
        when(mock.add(Mockito.anyString())).thenReturn(customerArray.add(Mockito.anyString()));
        //Проверяем, что можем добавить значение
        Assertions.assertEquals(mock.add("Twelve"), true);
    }

    @Test
    public void testAddAll(){
        CustomerArray customerArray1 = new CustomerArray();
        customerArray1.add("Thirteen");
        customerArray1.add("Fourteen");

        CustomerArray mock = mock(CustomerArray.class);
        when(mock.addAll(customerArray1)).thenReturn(customerArray.addAll(customerArray1));
        //Проверяем, что  можем добавить всю коллекцию CustomerArray
        Assertions.assertEquals(mock.addAll(customerArray1), true);
    }

    @Test
    public void testAddAllwithStringArray(){
        String [] arrayString = {"one","two"};

        CustomerArray mock = mock(CustomerArray.class);
        when(mock.addAll(arrayString)).thenReturn(customerArray.addAll(arrayString));
        //Проверяем, что  можем добавить весь массив
        Assertions.assertEquals(mock.addAll(arrayString), true);
    }

    @Test
    public void testGet() {
        CustomerArray mock = mock(CustomerArray.class);
        when(mock.get(4)).thenReturn("Five");
        //Проверяем получение значения массива по индексу
        Assertions.assertEquals(mock.get(4), customerArray.get(4));
    }

    @Test
    public void testContains(){
        CustomerArray mock = mock(CustomerArray.class);
        when(mock.contains("Two")).thenReturn(customerArray.contains("Two"));
        //Проверяем, метод contains
        Assertions.assertEquals(mock.contains("Two"), true);
    }

    @Test
    public void testClear(){
        CustomerArray mock = mock(CustomerArray.class);
        when(mock.clear()).thenReturn(customerArray.clear());
        //Проверяем, метод clear
        Assertions.assertEquals(mock.clear(), true);
    }

    @Test
    public void testGetSize() {
        CustomerArray mock = mock(CustomerArray.class);

        //Проверяем, что размер массива равен 10
        when(mock.getSize()).thenReturn(customerArray.getSize());
        Assertions.assertEquals(mock.getSize(), (10));

        //Добавляем записи в массив и проверяем, что размер  массива увеличился на 60%
        customerArray.add("Ten");
        customerArray.add("Eleven");
        when(mock.getSize()).thenReturn(customerArray.getSize());
        Assertions.assertEquals(mock.getSize(), (10 + (10 * 60 / 100)));
    }

    @Test
    public void testTrim(){
        CustomerArray mock = mock(CustomerArray.class);
        when(mock.trim()).thenReturn(customerArray.trim());
        //Проверяем, метод clear
        Assertions.assertEquals(mock.trim(), true);
    }

    @Test
    public void testCompate(){
       // CustomerArray mock = mock(CustomerArray.class);
        CustomerCollection mock = mock(CustomerCollection.class);
        when(mock.compate(customerArray)).thenReturn(customerArray.compate(customerArray));
        //Проверяем метод сравнения, что при сравнении одинаковых коллекций вернет true
        Assertions.assertEquals(mock.compate(customerArray), true);

        CustomerArray customerArray1 = new CustomerArray();
        customerArray1.add("Thirteen");
        customerArray1.add("Fourteen");

        when(mock.compate(customerArray1)).thenReturn(customerArray.compate(customerArray1));
        //Проверяем метод сравнения, что при сравнении неодинаковых коллекций вернет что коллекции неодинаковые
        Assertions.assertNotEquals(mock.compate(customerArray1), true);
    }

    @Test
    void testExpectedException() {
        CustomerArray customerArray1 = new CustomerArray();
        customerArray1.add("Thirteen");
        customerArray1.add("Fourteen");

        CustomerCollection mock = mock(CustomerCollection.class);
        //Проверка на RuntimeException
        when(mock.compate(customerArray1)).thenThrow(new RuntimeException());
         Assertions.assertThrows(RuntimeException.class, () -> {
             mock.compate(customerArray1);
         });
   }

    @Test
    void timeoutExceeded() {
        CustomerArray customerArray1 = new CustomerArray();
        for(int i = 0; i < 1000000; i++){
            customerArray1.add("addingText");
        }

        CustomerArray mock = mock(CustomerArray.class);
        when(mock.addAll(customerArray1)).thenReturn(customerArray.addAll(customerArray1));
        //Проверяем, что в коллекцию доавляется другая коллекия с 1000000элементами за 1секунду
           assertTimeout(ofMillis(1000), () -> {
               mock.addAll(customerArray1);
        });
    }

}

