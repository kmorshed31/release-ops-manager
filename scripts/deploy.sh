#!/bin/bash
set -e
ANSIBLE_HOST_KEY_CHECKING=False ansible-playbook ansible/deploy.yaml -i ansible/inventory.ini
