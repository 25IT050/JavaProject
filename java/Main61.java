interface switchable {
    void on();
    void off();
    boolean TorF();
    default void toggle() {
        if(TorF()){
            off();
        }
        else{
            on();
        }
    }
}
class fan implements switchable{
    boolean ans=false;
    public void on(){
        ans=false;
        System.out.println("fan on");
    }
    public void off(){
        ans=false;
        System.out.println("fan off");
    }
    public boolean TorF(){
        return ans;
    }
}
class light implements switchable{
    boolean ans=false;
    public void on(){
        ans=true;
        System.out.println("light on");
    }
    public void off(){
        ans=false;
        System.out.println("fan off");
    }
    public boolean TorF(){
        return ans;
    }
}
public class Main61 {
    public static void main(String[] args){
        switchable[] s1={new fan(),new light()};
        for(int i=0;i<2;i++){
            s1[i].toggle();
        }
        for(int i=0;i<2;i++){
            s1[i].toggle();
        }
    }
}