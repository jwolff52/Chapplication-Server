package chapplicationserver;

/**
 * @author James
 * @date Sep 18, 2013
 */
public class Protocol {
    public int processInput(String i) {
        if(i.charAt(0)=='-'){
            if(i.charAt(1)=='l'){
                switch(i.charAt(2)){
                    case 'i':
                        return 0;
                    case 'o':
                        return 1;
                }
            }else{
                switch(i.charAt(1)){
                    case 'r':
                        return 2;
                    case 'm':
                        return 3;
                }
            }
        }
        return -1;
    }
    
}
