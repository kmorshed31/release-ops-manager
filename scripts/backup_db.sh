#!/bin/bash
set -e
BACKUP_DIR="$(dirname "$0")/../backups"
mkdir -p "$BACKUP_DIR"
pg_dump -U releaseops_user -h localhost releaseopsdb > "$BACKUP_DIR/releaseops_$(date +"%Y%m%d_%H%M%S").sql"
echo "Backup saved to $BACKUP_DIR"
