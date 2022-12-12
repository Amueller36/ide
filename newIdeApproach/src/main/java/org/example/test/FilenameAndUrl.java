package org.example.test;

import java.util.ArrayList;

public record FilenameAndUrl(
        String filename,
        ArrayList<String> urls)
{
}
