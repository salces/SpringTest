package com.example.pl.slc.groovyTest


class FirstGroovyClass implements Comparable {

    def field = "Some field"

    public static void main(String[] args) {
        def var1 = new FirstGroovyClass()
        def var2 = new FirstGroovyClass()

        println var1 == var2

    }

    @Override
    int compareTo(Object o) {
        if (!(o instanceof FirstGroovyClass)) -1
        def casted = (FirstGroovyClass) o;
        if (this?.field == casted?.field) 0
    }
}
