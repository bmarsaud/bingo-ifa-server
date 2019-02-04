package fr.bmarsaud.bingoifa.server.model;

public interface DAO<T> {
    /**
     * Create an obj in the database
     * @param obj the object to insert
     * @return the created object updated with his id
     */
    T create(T obj);

    /**
     * Update an obj in the database
     * @param obj the object to update
     * @return true if the object is successfully updated, false instead
     */
    boolean update(T obj);

    /**
     * Delete an obj in the database
     * @param obj the object to delete
     * @return true if the object is successfully deleted, false instead
     */
    boolean delete(T obj);

    /**
     * Find an object by its id
     * @param id the id of the object to find
     * @return the object found, null instead
     */
    T find(int id);
}
