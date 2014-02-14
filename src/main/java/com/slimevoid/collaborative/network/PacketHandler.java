package com.slimevoid.collaborative.network;

import io.netty.buffer.ByteBufInputStream;

import java.io.DataInputStream;
import java.util.HashMap;
import java.util.Map;

import com.slimevoid.library.IPacketHandler;
import com.slimevoid.library.data.Logger;
import com.slimevoid.library.data.LoggerSlimevoidLib;
import com.slimevoid.library.network.handlers.SubPacketHandler;

import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.FMLNetworkEvent.ClientCustomPacketEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ServerCustomPacketEvent;

public class PacketHandler implements IPacketHandler {

    public static FMLEventChannel                 listener;

    private static Map<Integer, SubPacketHandler> handlers;

    /**
     * Initializes the commonHandler Map
     */
    public static void init() {
        handlers = new HashMap<Integer, SubPacketHandler>();
    }

    /**
     * Register a sub-handler with the server-side packet handler.
     * 
     * @param packetID
     *            Packet ID for the sub-handler to handle.
     * @param handler
     *            The sub-handler.
     */
    public static void registerPacketHandler(int packetID, SubPacketHandler handler) {
        if (handlers.containsKey(packetID)) {
            LoggerSlimevoidLib.getInstance(Logger.filterClassName(PacketHandler.class.toString())).write(false,
                                                                                                         "PacketID ["
                                                                                                                 + packetID
                                                                                                                 + "] already registered.",
                                                                                                         Logger.LogLevel.ERROR);
            throw new RuntimeException("PacketID [" + packetID
                                       + "] already registered.");
        }
        handlers.put(packetID,
                           handler);
    }

    /**
     * Retrieves the registered sub-handler from the server side list
     * 
     * @param packetID
     * @return the sub-handler
     */
    public static SubPacketHandler getPacketHandler(int packetID) {
        if (!handlers.containsKey(packetID)) {
            LoggerSlimevoidLib.getInstance(Logger.filterClassName(PacketHandler.class.toString())).write(false,
                                                                                                         "Tried to get a Packet Handler for ID: "
                                                                                                                 + packetID
                                                                                                                 + " that has not been registered.",
                                                                                                         Logger.LogLevel.WARNING);
            throw new RuntimeException("Tried to get a Packet Handler for ID: "
                                       + packetID
                                       + " that has not been registered.");
        }
        return handlers.get(packetID);
    }

    @Override
    public void onServerPacket(ServerCustomPacketEvent event) {
        DataInputStream data = new DataInputStream(new ByteBufInputStream(event.packet.payload()));
        try {
            int packetID = data.read();
            getPacketHandler(packetID).onPacketData(event);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onClientPacket(ClientCustomPacketEvent event) {
        DataInputStream data = new DataInputStream(new ByteBufInputStream(event.packet.payload()));
        try {
            int packetID = data.read();
            getPacketHandler(packetID).onPacketData(event);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
