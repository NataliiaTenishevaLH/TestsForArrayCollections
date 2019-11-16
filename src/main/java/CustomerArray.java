import java.util.Arrays;

public class CustomerArray implements CustomerCollection {
    private  int count = 0;
    private String[]  customerArray;

    public CustomerArray() {

        customerArray = new String[10];
    }

    private void refactoringCustomerArray(int length){
        String[]  newCustomerArray = new String[length];
        System.arraycopy(customerArray, 0, newCustomerArray, 0, customerArray.length);
        customerArray = newCustomerArray;
    }

    private void refactoringCustomerArray(int index, boolean moveItemLeft){
        String[]  newCustomerArray =  new String[customerArray.length];
        System.arraycopy(customerArray, 0, newCustomerArray, 0, customerArray.length);
        System.arraycopy(customerArray, index + 1, newCustomerArray, index, customerArray.length - index - 1);
        customerArray = newCustomerArray;
    }

    private void refactoringCustomerArray(int length, int countDeleted){
        String[]  newCustomerArray = new String[length];
        System.arraycopy(customerArray, 0, newCustomerArray, 0, (customerArray.length - countDeleted));
        customerArray = newCustomerArray;
    }

     //Узнать размер коллекции
    public int getSize(){
        return customerArray.length;
    }

    //Добавлять
    public boolean add(String value){
        if ( count == (customerArray.length - 1)){
            refactoringCustomerArray(customerArray.length + (customerArray.length * 60 / 100));
         }

         try {
            customerArray[count] = value;
            count++;
            return true;
        } catch (Exception e) {
             System.out.print(e.fillInStackTrace());
            return false;
        }
    }

    public boolean addIncreaseSizeBy1(String value){
        if ( count == (customerArray.length - 1)){
            refactoringCustomerArray(customerArray.length + 1);
        }

        try {
            customerArray[count] = value;
            count++;
            return true;
        } catch (Exception e) {
            System.out.print(e.fillInStackTrace());
            return false;
        }
    }

    @Override
    public boolean addAll(String[] strArr) {
        try {
             for(int i = 0; i < strArr.length; i++){
                 add(strArr[i]);
             }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean addAll(CustomerArray strColl) {
         try {
             String [] anotherCustomerArray = strColl.customerArray;
             for(int i = 0; i < (strColl.getSize()); i++){
                 add(anotherCustomerArray[i]);
             }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(int index) {
        if (index > count) {
            return false;
        }
        refactoringCustomerArray(index, true);
        return true;
    }

    @Override
    public boolean delete(String value) {
        if (!contains(value)){
            return false;
        }

        for(int i = 0; i < this.customerArray.length; i++){
            if (customerArray[i].toString() == value) {
                refactoringCustomerArray(i, true);
                return true;
            }
        }
        return false;
    }

    @Override
    public String get(int index) {
        if (index > count) {
            return null;
        }

        for(int i = 0; i < customerArray.length; i++){
            if(indexOf(customerArray[i]) == index) {
                return customerArray[i];
            }
        }
        return null;
    }

    public boolean contains(String value){
        for (String element : customerArray){
            if (element == null){
                continue;
            }
            if (element == value){
                return true;
            }
        }
        return false;
    }

      //Находить индекс элемента
      public int indexOf(String value){
        for(int i = 0; i < customerArray.length; i++){
            if((customerArray[i].toString()) == value) {
                return i;
            }
        }
        return 0;
    }

    //Метод очистить
    public boolean  clear(){
        try {
            Arrays.fill(customerArray, null);
            return true;
        } catch (Exception e) {
            return false;
        }

    }


    //Метод trim
    public boolean trim(){
        int countDeleted = 0;
        for(int i = 0; i < customerArray.length; i++){
             if (customerArray[i] == null) {
                 countDeleted++;
             }
        }

        try {
            refactoringCustomerArray(customerArray.length - countDeleted, countDeleted);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean compate(CustomerArray coll) {
        if (coll == null) return false;
        if (getSize() != coll.getSize()) return false;
        for(String element : customerArray){
            if (element == null){
                continue;
            };
            if (!coll.contains(element)){
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return Arrays.toString(customerArray);
    }

}
