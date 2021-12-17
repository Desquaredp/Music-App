package edu.usfca.cs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
/**
 * This is the parent class for Song, ARtist, ALbum.
 * It contains the instance variables that are shared by those classes.
 *
 * @author Deep Mistry
 **/
public class Entity {
    protected String name;
    protected static int counter = 0;
    protected int entityID;
    protected Date dateCreated;
    protected String mood;
    protected String genre;

    /**
     * Constructor for Entity
     */
    public Entity() {
        this.name = "";
        counter++;
        this.entityID = counter;
        dateCreated = new Date();
        this.mood = "";
        this.genre = "";

    }

    /**
     * checks if an entity object already exists
     * @param otherEntity the entity object to be checked
     * @return returns a boolean value
     */
    public boolean equals(Entity otherEntity) {
        return entityID == otherEntity.entityID;
    }

    /**
     * Construtor
     * @param name takes in the name of the Entity object
     */
    public Entity(String name) {
        this.name = name;
        counter++;
        this.entityID = counter;
        dateCreated = new Date();
        this.mood = "";
        this.genre = "";
    }

    /**
     * getter for DateCreated
     * @return returns the date created
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * setter for Date created
     * @param dateCreated takes in a date value
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**getter
     *
     * @return returns a name of the Entity
     */
    public String getName() {
        return name;
    }

    /**
     * setter for name
     * @param name takes in a string value containing the name of the entity
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * creates an XML script with the name of the Entity and the ID in it
     * @return XML string that can be executed in an xml file
     */

    public String toXML() {
        return "<entity><name>" + this.name + "</name><ID> " + this.entityID + "</ID></entity>";
    }

    /**
     * is overwritten in Song, Entity, Album classes
     * @return  returns an empty string
     */
    public String toSQL() {
        return " ";}

    /**
     * is overwritten in Song, Entity, Album classes
     * @param rs takes in a result set
     * @throws SQLException
     */

    public void fromSQL(ResultSet rs) throws SQLException {
    }

    /**
     * used to alter the single inverted comma into a two single inverted commas in the toSQL() methods
     * in Song, Artist, ALbum.
     * @param w takes in the stirng to be checked
     * @return return either an altered string or the original string if the condition isn't met
     */
    public String stripPunct(String w) {

        for (int i = 0; i < w.length(); i++) {

            if(w.charAt(i) == '\''){
                String result = w.substring(0,i)+ "'" + w.substring(i);
                return result;
            }
        }
        return w ;
    }
}
