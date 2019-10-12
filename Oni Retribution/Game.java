
public class Game
{
    private Room aCurrentRoom;
    private Parser aParser;

    /**
     * Construit "l'univers" de d'une nouvelle partie (créer les rooms, objets,etc...)
     */
    public Game()
    { this.createRooms();
        aParser=new Parser();
    }

    /**
     * Procédure privée qui permet de définir les lieux et leurs sorties respectives
     */
    private void createRooms()
    {// Creation des 5 lieux
        Room vFMountain=new Room("in the Fuyikasane Mountain");
        Room vNTown=new Room("in a small town");
        Room vNMountain=new Room("in the Natagumo Mountain");
        Room vHTemple=new Room("in the Hashira Temple");
        Room vRLDistrict=new Room("in the Red Light District");
        Room vSVillage=new Room("in the Swordsmith Village");
        Room vTrain=new Room("on a moving Train");
        Room vAsakusa=new Room("in Asakusa, a Tokyo district");
        Room vForest1=new Room("in a hostile forest");
        Room vForest2=new Room("in a hostile forest");
        Room vBEstate=new Room("in the Butterfly Estate");
        Room vCity=new Room("in a neighboring City");
        Room vKagaya=new Room("at Kagaya's place");
        Room vDIFortress=new Room("in the Dimensional Infinity Fortress");

        // Creation reseau
        // vMountain.setExits(vForest,vTown,null,null);
        // vTown.setExits(vMountain,vHashira,vAsaka,null);
        // vAsaka.setExits(null,null,null,vTown);
        // vForest.setExits(null,vMountain,null,null);
        // vHashira.setExits(vTown,null,null,null);
        vFMountain.setExits("below",vNTown);
        vFMountain.setExits("east",vForest1);
        vNTown.setExits("above",vFMountain);
        vNTown.setExits("east",vAsakusa);
        vNMountain.setExits("below",vHTemple);
        vNMountain.setExits("west",vForest1);
        vHTemple.setExits("above",vNMountain);
        vHTemple.setExits("north",vCity);
        vHTemple.setExits("south",vForest2);
        vHTemple.setExits("west",vBEstate);
        vRLDistrict.setExits("south",vSVillage);
        vRLDistrict.setExits("east",vTrain);
        vSVillage.setExits("north",vRLDistrict);
        vTrain.setExits("east",vCity);
        vTrain.setExits("west",vRLDistrict);
        vAsakusa.setExits("west",vNTown);
        vForest1.setExits("east",vNMountain);
        vForest1.setExits("west",vFMountain);
        vForest2.setExits("north",vHTemple);
        vForest2.setExits("south",vKagaya);
        vBEstate.setExits("east",vHTemple);
        vCity.setExits("north",vTrain);
        vCity.setExits("south",vHTemple);
        vKagaya.setExits("insideTheVortex",vDIFortress);
        vKagaya.setExits("north",vForest2);
        vDIFortress.setExits("outside",vKagaya);
        vFMountain.setExits("blinkToKagaya",vKagaya);

        // Initialise lieu courant
        this.aCurrentRoom=vFMountain;

    }

    /**
     * Procédure privée qui permet de changer de lieu si la commande de déplacement est viable
     */
    private void goRoom(final Command pDirect)
    { 
        if (pDirect.getSecondWord()==null) 
        {
            System.out.println("Go where?");
            return;
        }
        String vDirection=pDirect.getSecondWord();
        Room vNextRoom=this.aCurrentRoom.getExit(vDirection);

        // if (!vDirection.equals(aCurrentRoom.getExitString())){
        // System.out.println("Unknown Direction !");}

        if (vNextRoom==null)
        {System.out.println("You can't go this way");
            return;
        }
        else
        {  
            aCurrentRoom=vNextRoom;
            this.printLocationInfo();

        }
    }

    /**
     * Affiche un message de bienvenue lors de la création d'une nouvelle partie et indique la position actuelle du personnage
     */
    private void printWelcome()
    { System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");

        this.printLocationInfo();
    }

    /**
     * Affiche un texte qui est censé aider à avancer si on est bloqué.
     */
    private void printHelp()
    {System.out.println("You are lost. You are alone."+
            "You wander around at the university."+

            "Your command words are:"+
            " go quit help");
    }

    /**
     * Procedure qui permet de quitter une partie
     */
    private boolean quit(final Command pCommand)
    {if (pCommand.getSecondWord()!=null){
            System.out.println("Quit What?");
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * Procédure qui détecte et permet l'utilisation d'une commande
     */
    private boolean processCommand(final Command pCommand)
    {
        if (pCommand.isUnknown()==true)
        {
            System.out.println("I don't know what you mean");
            return false;
        }
        if (pCommand.getCommandWord().equals("quit"))
        {
            return this.quit(pCommand);
        } 
        if (pCommand.getCommandWord().equals("help"))
        {
            this.printHelp();
            return false;
        }
        if (pCommand.getCommandWord().equals("go")){
            this.goRoom(pCommand);
            return false;
        }
        return false;
    }

    /**
     * Création d'une nouvelle partie
     */
    public void play()
    { this.printWelcome();
        Boolean vFinished=false;
        while (vFinished==false)
        {
            Command vCommand=this.aParser.getCommand();
            vFinished=this.processCommand(vCommand);
        }
        System.out.println("Thank you for playing.  Good bye.");

    }

    /**
     * Methode dans l'unique but d'afficher la location
     */

    private void printLocationInfo()
    {
        System.out.println("You are "+aCurrentRoom.getDescription()+"\n");
        System.out.println(aCurrentRoom.getExitString());
    }

}

// Game
