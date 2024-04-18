import re

def remove_secrets(data):
    patterns = [
        re.compile(rb'AccessKey ID:\s*\S+'),
        re.compile(rb'AccessKey Secret:\s*\S+')
    ]
    for pattern in patterns:
        data = pattern.sub(b'AccessKey ID: [REMOVED]', data)
        data = pattern.sub(b'AccessKey Secret: [REMOVED]', data)
    return data

def blob_callback(blob, ctx):
    if ctx.filename == b'src/main/resources/application-dev.yml':
        blob.data = remove_secrets(blob.data)