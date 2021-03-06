package io.krakens.grok.api;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import com.google.code.regexp.Matcher;
import com.google.code.regexp.Pattern;


/**
 * {@code GrokUtils} contain set of useful tools or methods.
 *
 * @since 0.0.6
 */
public class GrokUtils {

  /**
   * Extract Grok patter like %{FOO} to FOO, Also Grok pattern with semantic.
   */
  public static final Pattern GROK_PATTERN = Pattern.compile(
      "%\\{"
          + "(?<name>"
          + "(?<pattern>[A-z0-9]+)"
          + "(?::(?<subname>[A-z0-9_:;,\\-\\/\\s\\.']+))?"
          + ")"
          + "(?:=(?<definition>"
          + "(?:"
          + "(?:[^{}]+|\\.+)+"
          + ")+"
          + ")"
          + ")?"
          + "\\}");

  public static final Pattern NAMED_REGEX = Pattern
      .compile("\\(\\?<([a-zA-Z][a-zA-Z0-9_]*)>");

  public static Set<String> getNameGroups(String regex) {
    Set<String> namedGroups = new LinkedHashSet<>();
    Matcher matcher = NAMED_REGEX.matcher(regex);
    while (matcher.find()) {
      namedGroups.add(matcher.group(1));
    }
    return namedGroups;
  }
}
