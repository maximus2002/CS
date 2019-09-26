#!/usr/bin/python
# CS 6250 Spring 2019 - Project 6 - SDN Firewall

from pyretic.lib.corelib import *
from pyretic.lib.std import *
from pyretic.lib.query import packets
from pyretic.core import packet
from collections import namedtuple
 
def make_firewall_dicts(config):
    # Create a list of firewall policy
    policy_list = {}
    for entry in config:
        policy_list [entry['rulenum']] = Policy(entry['macaddr_src'], entry['macaddr_dst'], entry['ipaddr_src'], entry['ipaddr_dst'], entry['port_src'], entry['port_dst'], entry['protocol'])
        values = policy_list.values()
    return policy_list

Policy = namedtuple('Policy', ('macaddr_src', 'macaddr_dst','ipaddr_src','ipaddr_dst','port_src','port_dst','protocol'))

def make_firewall_policy(config):
    # You may place any user-defined functions in this space.
    # You are not required to use this space - it is available if needed.
    
    # feel free to remove the following "print config" line once you no longer need it
    print config # for demonstration purposes only, so you can see the format of the config

    Fpolicies = make_firewall_dicts(config)


    rules = []
    for policy in Fpolicies.itervalues():
        rule = make_firewall_rules(policy)
        rules.append(rule)
        pass

    allowed = ~(union(rules))
    #print allowed
    return allowed



def make_firewall_rules(policy):
    rule = match(ethtype=packet.IPV4)

    #Check protocol
    if (policy.protocol != '-'):
        if (policy.protocol == 'T'):
            rule = rule & match(protocol = 6)
        elif (policy.protocol == 'U'):
            rule = rule & match(protocol = 17)
        elif (policy.protocol == 'I'):
            rule = rule & match(protocol = 1)  
	elif (policy.protocol == 'B'):
            rule2 = rule & match(protocol = 6)
	    rule = rule2 & match(protocol = 17)
    #Protocol - do not match anything
    elif (policy.protocol == '-'):
	rule = rule & match(protocol = 999)    
    #Check mac address
    if ((policy.macaddr_src != '-') and (policy.macaddr_dst != '-')):
        rule &= match(srcmac = EthAddr(policy.macaddr_src), dstmac = EthAddr(policy.macaddr_dst))
    elif (policy.macaddr_src != '-'):
        rule &= match(srcmac = EthAddr(policy.macaddr_src))
    elif (policy.macaddr_dst != '-'):
        rule &= match(dstmac = EthAddr(policy.macaddr_dst))

    #Check ip address
    if ((policy.ipaddr_src != '-') and (policy.ipaddr_dst != '-')):
        rule = rule & match(srcip = IPAddr(policy.ipaddr_src), dstip=IPAddr(policy.ipaddr_dst))
    elif (policy.ipaddr_dst != '-'):
        rule = rule & match(dstip = IPAddr(policy.ipaddr_dst))
    elif (policy.ipaddr_src != '-'):
        rule = rule & match(srcip = IPAddr(policy.ipaddr_src))

    #Check port
    if ((policy.port_src != '-') and (policy.port_dst != '-')):
        rule = rule & match(srcport = int(policy.port_src), dstport = int(policy.port_dst))
    elif (policy.port_dst != '-'):
        rule = rule & match(dstport = int(policy.port_dst))
    elif (policy.port_src != '-'):
        rule = rule & match(srcport = int(policy.port_src))


    return rule



