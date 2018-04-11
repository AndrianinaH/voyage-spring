package M1.voyage.utilitaire;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Fonction {
   
	public static String getDate()
	{
		String format = "dd-MM-yyyy"; 
		SimpleDateFormat formater = new SimpleDateFormat(format); 
		java.util.Date date = new java.util.Date(); 
		return formater.format(date); 
	}
	public static String getHeure()
	{
		String format = "HH:mm"; 
		SimpleDateFormat formater = new SimpleDateFormat(format); 
		java.util.Date date = new java.util.Date(); 
		return formater.format(date); 
	}

	public static String formatDate(Date d)
	{
		Format f = new SimpleDateFormat("dd-MM-yyyy");
		return f.format(d);
	}

	public static String transformHourToDay(int heure)
	{
		String ret="";
		int jour=heure/8;
		int hour=heure%8;
		ret=String.valueOf(jour)+"j "+String.valueOf(hour)+"h";
		return ret;
	}
	public static String toUpperFirst(String data)
	{
		String lower=data.toLowerCase();
		String uper=data.toUpperCase();
		return uper.charAt(0)+lower.substring(1);
		
	}
	public static boolean isPrimitif(String type){
        List<String> rep = new ArrayList<>();
        rep.add("int");
        rep.add("double");
        rep.add("float");
        rep.add("String");
        rep.add("Date");
        return rep.contains(type);
    }
	public static ArrayList<Field> getOtherAttribut(Field[]attributs){
		ArrayList<Field> otherAttribut=new ArrayList<Field>();
        for(int i=0;i<attributs.length;i++)
        {
        	if(!Fonction.isPrimitif(attributs[i].getType().getSimpleName())){
        		if(!attributs[i].getType().getSimpleName().contains("List")){
       			 	otherAttribut.add(attributs[i]);
        		}	
            }
        }
        return otherAttribut;
    }
	public static ArrayList<Field> getAttributList(Field[]attributs){
		ArrayList<Field> otherAttribut=new ArrayList<Field>();
        for(int i=0;i<attributs.length;i++)
        {
        	if(!Fonction.isPrimitif(attributs[i].getType().getName())){
        		if(attributs[i].getType().getName().contains("List")){
        			//System.out.println("List "+attributs[i]);
       			 	otherAttribut.add(attributs[i]);
        		}	
            }
        }
        return otherAttribut;
    }
    //-------------- pour hibernate Dao
	public static Field[] getFieldsExceptList(Field[] fields){
        ArrayList<Field> ret = new ArrayList<Field>();
        for(Field field : fields){
            if(!field.getType().getSimpleName().equals("List")){
                ret.add(field);
            }

        }
        return ret.toArray(new Field[ret.size()]);
    }
	
	public static Class instanceListObject(Field attribut) throws InstantiationException, IllegalAccessException{
		return (Class)((ParameterizedType)attribut.getGenericType()).getActualTypeArguments()[0];
	}
    
}

