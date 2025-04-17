package monitor.test;

import org.pcap4j.core.Pcaps;
import org.pcap4j.core.PcapNetworkInterface;

public class NetworkInterfaces {
    public static void main(String[] args) throws Exception {
        for (PcapNetworkInterface nif : Pcaps.findAllDevs()) {
            System.out.println(nif.getName() + " : " + nif.getDescription());
        }
    }
}
