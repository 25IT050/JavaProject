class outofstock extends Exception{
    int shortfall;
    outofstock(String s1,int shortfall){
        super(s1);
        this.shortfall=shortfall;
    }
}
class invalidquantity extends Exception{
    invalidquantity(String s2){
        super(s2);
    }
}
class warehouse{
    int stock;
    warehouse(int stock){
        this.stock=stock;
    }
    void issu(String item,int qut) throws outofstock , invalidquantity{
        if(qut<=0){
            throw new invalidquantity("invalide quantity");
        }
        if(qut>stock){
            throw new outofstock("out of stock", qut-stock);
        }
        stock = stock - qut ;
        System.out.println(item+"successfull transfer");
    }
}
public class Main82 {
    public static void main(String[] args){
        warehouse w1=new warehouse(10);
        String[] items = {"Pen", "Book", "Pencil", "Bag"};
        int[] qty = {3, 5, 0, 8};
        for (int i = 0; i < items.length; i++) {

            try {
                w1.issu(items[i], qty[i]);
            }
            catch (outofstock e) {
                System.out.println("out of stock Shortfall = " + e.shortfall);
            }
            catch (invalidquantity e) {
                System.out.println("invalid quantity");
            }
            finally{
                System.out.println("successfull");
            }

        }
    }    
}