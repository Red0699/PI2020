/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
import java.util.HashMap;
import java.util.Map;

public class RSObjectArray {

    private Map<Object, Object> map = null;

    public RSObjectArray() {
        this.map = new HashMap<>();
    }
    
    public void add(Object key,Object value){
        map.put(key, value);
    }
    
    public Object getValue(Object key){
        return this.map.get(key).toString();
    }
    
    public Object getAll(){
        return this.map.toString();
    }
   
}
