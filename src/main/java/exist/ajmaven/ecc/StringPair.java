package exist.ajmaven.ecc;
public class StringPair{
    private String key;
    private String value;

    public StringPair(String aKey, String aValue)
    {
        key   = aKey;
        value = aValue;
    }

    public void setKey(String aKey) { key = aKey; }
    public void setValue(String aValue) { value= aValue; }

    public String key()   { return key; }
    public String value() { return value; }
    public String merged() { return key+value; }

    public String getRandomString() {
        String randomString = "";
        for (int i = ((int)(Math.random() * 5) + 1); i > 0; i--) {
            randomString += (char)((int)(Math.random() * 95) + 32);
        }
        return randomString;
    }

    // public int compareTo(StringPair pair1, StringPair pair2) {
    
    //     return pair1.merged().compareTo(pair2.merged());
        
    //     //descending order
    //     //return compareQuantity - this.quantity;
        
    // }   
}