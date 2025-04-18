function drawTimeChart(packets) {
    const chartData = processDateStats(packets);
    // console.log(chartData);

    var data = google.visualization.arrayToDataTable(chartData);

    var options =
    {
        'title': 'Packets by time',
        'width': 500,
        'height': 300,
        'hAxis': {title: 'Date',  titleTextStyle: {color: '#333'}},
        'vAxis': {minValue: 0},
        'backgroundColor': { fill:'transparent' },

    };

    var chart = new google.visualization.AreaChart(document.getElementById('packets-by-time'));
    chart.draw(data, options);
}
function processDateStats(packets) {
    const dateMap = {};
    const ips = new Set();
    
    // Process packets
    packets.forEach(packet => {
        const date = new Date(packet.arrival_time).toISOString().split('T')[0];
        const dstIp = packet.dst_ip || 'unknown';
        
        if (!dateMap[date]) {
            dateMap[date] = {};
        }
        
        dateMap[date][dstIp] = (dateMap[date][dstIp] || 0) + 1;
        ips.add(dstIp);
    });
    
    // Get sorted unique dates
    const dates = Object.keys(dateMap).sort();
    
    // Get sorted unique IPs
    const sortedIps = Array.from(ips).sort();
    
    // Create result array
    const result = [];
    
    // Add header
    result.push(['Date', ...sortedIps]);
    
    // Add rows
    dates.forEach(date => {
        const row = [date];
        sortedIps.forEach(ip => {
            row.push(dateMap[date][ip] || 0);
        });
        result.push(row);
    });
    
    return result;
}
