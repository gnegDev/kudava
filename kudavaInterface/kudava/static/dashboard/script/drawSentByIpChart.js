function drawSentByIpChart(packets) {

    const ipCount = {};
            
    packets.forEach(packet => {
        const ip = packet.src_ip || 'unknown';
        ipCount[ip] = (ipCount[ip] || 0) + 1;
    });
    
    // Convert to array and sort by count descending
    // Create the data table.
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'IP');
    data.addColumn('number', 'Packets');
    data.addRows(Object.entries(ipCount).sort((a, b) => b[1] - a[1]));

    // Set chart options
    var options = 
    {
        'title': 'Packets sent by',
        'backgroundColor': { fill:'transparent' },
        'width': 300,
        'height': 300,
        'pieHole': 0.4
    };

    // Instantiate and draw our chart, passing in some options.
    var chart = new google.visualization.PieChart(document.getElementById('from-chart'));
    chart.draw(data, options);
}