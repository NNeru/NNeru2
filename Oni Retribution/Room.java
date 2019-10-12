import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
public class Room
{
    private String aDescription;
    private HashMap<String, Room> aExits;
    // ###Constructeur Naturel###
    /**
     * crée un lieu
     */
    public Room(final String pDesc)
    {
        this.aDescription=pDesc;
        aExits = new HashMap<String, Room>();

    }
    // ### Accesseur ###
    /**
     * Donne accès à la description d'un lieu
     */
    public String getDescription()
    {return this.aDescription;

    }

    /**
     * Permet d'avoir accès aux sorties de chaque lieux
     */
    public Room getExit (final String pDirection)
    {

        return aExits.get(pDirection);
    }

    /**
     * Créer les sorties de chaque lieux
     */
    public void setExits(final String pDirection, final Room pLieuVoisin) 
    {   
        aExits.put(pDirection, pLieuVoisin);
    }

    /**
     * Methode qui retourne la liste de toutes les sorties de la pièce
     */

    public String getExitString()
    {
        String vReturnString="Exits:";
        Set<String> keys=aExits.keySet();
        for(String iExit : keys){
            vReturnString += " "+ iExit;

        }

        return vReturnString;
    }
}

