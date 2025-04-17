package monitor;



import java.io.IOException;

import com.sun.jna.Platform;

import org.pcap4j.core.*;
import org.pcap4j.core.BpfProgram.BpfCompileMode;
import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;
import org.pcap4j.packet.Packet;
import org.pcap4j.util.NifSelector;

public class PacketCapture {

    static PcapNetworkInterface getNetworkDevice() {
        PcapNetworkInterface device = null;
        try {
            device = new NifSelector().selectNetworkInterface();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return device;
    }

    public static void main(String[] args) throws PcapNativeException, NotOpenException {
        
        PcapNetworkInterface device = getNetworkDevice();
        System.out.println("You chose: " + device);

        
        if (device == null) {
            System.out.println("No device chosen.");
            System.exit(1);
        }

        
        int snapshotLength = 65536; 
        int readTimeout = 50; 
        final PcapHandle handle;
        handle = device.openLive(snapshotLength, PromiscuousMode.PROMISCUOUS, readTimeout);
        PcapDumper dumper = handle.dumpOpen("out.pcap");

        
        String filter = "tcp port 80";
        handle.setFilter(filter, BpfCompileMode.OPTIMIZE);

        
        PacketListener listener = packet -> {

            System.out.println(handle.getTimestampPrecision());
            System.out.println(packet);


            try {
                dumper.dump(packet);
            } catch (NotOpenException e) {
                e.printStackTrace();
            }
        };

        
        try {
            int maxPackets = 50;
            handle.loop(maxPackets, listener);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        
        PcapStat stats = handle.getStats();
        System.out.println("Packets received: " + stats.getNumPacketsReceived());
        System.out.println("Packets dropped: " + stats.getNumPacketsDropped());
        System.out.println("Packets dropped by interface: " + stats.getNumPacketsDroppedByIf());
        
        if (Platform.isWindows()) {
            System.out.println("Packets captured: " + stats.getNumPacketsCaptured());
        }

        
        dumper.close();
        handle.close();
    }
}


