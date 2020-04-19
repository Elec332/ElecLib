/**
 * Created by Elec332 on 19-4-2020
 */
module nl.elec332.lib.eleclib {

    exports nl.elec332.lib.java.io;
    exports nl.elec332.lib.java.network;
    exports nl.elec332.lib.java.swing;
    exports nl.elec332.lib.java.util;
    exports nl.elec332.lib.java.util.function;
    exports nl.elec332.lib.java.util.image;
    exports nl.elec332.lib.java.util.reference;

    exports nl.elec332.lib.netty;
    exports nl.elec332.lib.netty.packet;
    exports nl.elec332.lib.netty.packets;

    requires java.desktop;
    requires com.google.common;
    // Remove as soon as new spec is available: https://github.com/google/guava/issues/2960#issuecomment-595946238
    requires jsr305;

    requires static io.netty.all;

}