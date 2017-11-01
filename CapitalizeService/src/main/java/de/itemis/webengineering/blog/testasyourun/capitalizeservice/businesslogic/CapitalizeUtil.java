package de.itemis.webengineering.blog.testasyourun.capitalizeservice.businesslogic;

import com.google.common.base.Preconditions;

public class CapitalizeUtil
{
    public static String capitalize(final String toCapitalize)
    {
        Preconditions.checkArgument(toCapitalize != null, "String to capitalize mustn't be null");
        if (toCapitalize.isEmpty())
        {
            return toCapitalize;
        }
        else
        {
            return toCapitalize.substring(0, 1).toUpperCase() + toCapitalize.substring(1);
        }
    }
}
