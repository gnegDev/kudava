package monitor.test;

import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.Pcaps;
import org.pcap4j.packet.Packet;
import org.pcap4j.util.NifSelector;
import org.pcap4j.util.NifSelector;

public class PacketCapture {
    public static void main(String[] args) throws Exception {

        //PcapNetworkInterface nif = Pcaps.getDevByName("\\Device\\NPF_{C630822A-72C4-4E41-B30B-75F4D143457B} : WAN Miniport (Network Monitor)");
        PcapNetworkInterface nif = new NifSelector().selectNetworkInterface();
        PcapHandle handle = nif.openLive(65536, PcapNetworkInterface.PromiscuousMode.PROMISCUOUS, 10);
        
        while (true) {
            Packet packet = handle.getNextPacketEx();

            System.out.println(packet);
        }
    }
}
