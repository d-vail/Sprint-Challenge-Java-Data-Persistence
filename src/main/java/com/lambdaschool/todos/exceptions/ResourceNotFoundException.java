package com.lambdaschool.todos.exceptions;

import java.util.HashMap;
import java.util.Map;

/**
 * A custom exception to be thrown when a resource is not found
 */
public class ResourceNotFoundException extends Exception {
  /**
   * Default Constructor passes message to Exception parent.
   *
   * @param resourceClass     The class for the resource type that was requested
   * @param searchParameters  A variable number of search parameters key, value pairs
   */
  public ResourceNotFoundException(Class resourceClass, String...searchParameters)
  {
    super(String.format("%s was not found for %s", resourceClass.getSimpleName(), toMap(searchParameters)));
  }

  /**
   * Convert variable string search parameters into a map.
   *
   * @param entries A variable number of search parameters
   * @return        A hash map containing the search parameters
   */
  private static Map<String, String> toMap(String...entries) {
    if(isEvenKeyValues(entries)) {
      throw new IllegalArgumentException("Invalid key-value entries");
    }

    HashMap<String, String> hashMap = new HashMap<>();

    for(int i = 0; i < entries.length/2; i++) {
      int key = i * 2;
      int value = key + 1;
      hashMap.put(entries[key], entries[value]);
    }

    return hashMap;
  }

  /**
   * Check if a valid number of string arguments exist to convert into a key-value map.
   *
   * @param entries A variable number of search parameters
   * @return        True/False
   */
  private static boolean isEvenKeyValues(String...entries) {
    return entries.length % 2 == 1;
  }
}
